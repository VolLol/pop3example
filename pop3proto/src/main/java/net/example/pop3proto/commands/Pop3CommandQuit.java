package net.example.pop3proto.commands;

import net.example.pop3proto.Pop3CommandType;

public class Pop3CommandQuit implements Pop3Command {
    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.QUIT;
    }

    @Override
    public void parseAndSetArgumentsFromString(String arguments) {

    }
}
