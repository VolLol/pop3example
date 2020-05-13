package net.example.server;

import net.example.pop3proto.Pop3CommandParser;
import net.example.pop3proto.commands.*;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.UserRepository;
import net.example.server.usecases.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Pop3ServerProcessor {
    private final Pop3SessionContext sessionContext;
    private final BufferedReader socketIn;
    private final PrintWriter socketOut;
    private final ListMailBoxUseCase listMailBoxUseCase;
    private final StatMailBoxUseCase statMailBoxUseCase;
    private final NoopMailBoxUseCase noopMailBoxUseCase;
    private final TopMailBoxUseCase topMailBoxUseCase;
    private final RetrMailBoxUseCase retrMailBoxUseCase;
    private final DeleMailBoxUseCase deleMailBoxUseCase;
    private final ApopMailBoxUseCase apopMailBoxUseCase;
    private final RsetMailBoxUseCase rsetMailBoxUseCase;


    public Pop3ServerProcessor(Pop3SessionContext sessionContext, BufferedReader socketIn, PrintWriter socketOut) {
        this.socketIn = socketIn;
        this.socketOut = socketOut;
        this.sessionContext = sessionContext;
        MailBoxRepository mailBoxRepository = new MailBoxRepository();
        UserRepository userRepository = new UserRepository();
        this.listMailBoxUseCase = new ListMailBoxUseCase(sessionContext, mailBoxRepository);
        this.statMailBoxUseCase = new StatMailBoxUseCase(sessionContext, mailBoxRepository);
        this.topMailBoxUseCase = new TopMailBoxUseCase(sessionContext, mailBoxRepository);
        this.retrMailBoxUseCase = new RetrMailBoxUseCase(sessionContext, mailBoxRepository);
        this.deleMailBoxUseCase = new DeleMailBoxUseCase(sessionContext, mailBoxRepository);
        this.apopMailBoxUseCase = new ApopMailBoxUseCase(sessionContext, userRepository);
        this.noopMailBoxUseCase = new NoopMailBoxUseCase(sessionContext);
        this.rsetMailBoxUseCase = new RsetMailBoxUseCase(sessionContext, mailBoxRepository);
    }

    public void execute() throws IOException {
        ArrayList<String> outBuffer = new ArrayList<>();
        String readLine;

        socketOut.println("+OK POP3 server ready <1896.697170952@dbc.mtview.ca.us>");
        socketOut.flush();
        System.out.println("[" + sessionContext.getClientIP() + "]" + " ready for commands");
        while ((readLine = socketIn.readLine()) != null) {
            try {

                System.out.println("[" + sessionContext.getClientIP() + "]" + " user send data = " + readLine);
                outBuffer.clear();

                Pop3Command command = Pop3CommandParser.parse(readLine);
                System.out.println("[" + sessionContext.getClientIP() + "]" + " detect command = " + command.getCommandType());

                if (command instanceof Pop3CommandList) {
                    outBuffer.addAll(listMailBoxUseCase.execute(((Pop3CommandList) command).getLimit()));
                }

                if (command instanceof Pop3CommandStat) {
                    outBuffer.addAll(statMailBoxUseCase.execute());
                }

                if (command instanceof Pop3CommandNoop) {
                    outBuffer.addAll(noopMailBoxUseCase.execute());
                }

                if (command instanceof Pop3CommandTop) {
                    outBuffer.addAll(topMailBoxUseCase.execute(((Pop3CommandTop) command).getMailIndex(),
                            ((Pop3CommandTop) command).getCountLines()));
                }

                if (command instanceof Pop3CommandRetr) {
                    outBuffer.addAll(retrMailBoxUseCase.execute(((Pop3CommandRetr) command).getMailIndex()));
                }

                if (command instanceof Pop3CommandDele) {
                    outBuffer.addAll(deleMailBoxUseCase.execute(((Pop3CommandDele) command).getMailIndex()));
                }

                if (command instanceof Pop3CommandApop) {
                    outBuffer.addAll(apopMailBoxUseCase.execute(((Pop3CommandApop) command).getUsername(), ((Pop3CommandApop) command).getCryptPassword()));
                }

                if (command instanceof Pop3CommandRset) {
                    outBuffer.addAll(rsetMailBoxUseCase.execute());
                }
                for (String data : outBuffer) {
                    socketOut.println(data);
                }
            } catch (Pop3UnknownCommand e) {
                System.out.println("[" + sessionContext.getClientIP() + "]" + " ERR unknown command ");
                socketOut.println("-ERR unknown command");
            }
            socketOut.flush();
        }
    }


}
