package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandType;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;

public class Pop3CommandDele implements Pop3Command {

    @Getter
    private int mailIndex;

    public Pop3CommandDele() {
    }

    public Pop3CommandDele(int mailIndex) {
        this.mailIndex = mailIndex;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.DELE;
    }

    @Override
    public void parseAndSetArgumentsFromString(String arguments) {
        if (arguments == null) {
            throw new Pop3UnknownCommand("Unknown Command");
        }

        String argumentsPrepared = arguments.trim();
        this.mailIndex = Integer.parseInt(argumentsPrepared);

    }
}
