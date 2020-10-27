package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandType;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;

public class Pop3CommandRetr implements Pop3Command {

    @Getter
    private int mailIndex;

    public Pop3CommandRetr() {
    }

    public Pop3CommandRetr(int mailIndex) {
        this.mailIndex = mailIndex;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.RETR;
    }

    @Override
    public void parseAndSetArgumentsFromString(String arguments) {
        if (arguments == null || arguments.contains("[0-9]+")) {
            throw new Pop3UnknownCommand("Unknown Command");

        } else {
            String argumentsPrepared = arguments.trim();

            this.mailIndex = Integer.parseInt(argumentsPrepared);
        }

    }
}
