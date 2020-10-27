package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandParser;
import net.example.pop3proto.Pop3CommandType;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;

public class Pop3CommandPass implements Pop3Command {

    @Getter
    private String cryptPassword;

    public Pop3CommandPass() {
    }

    public Pop3CommandPass(String cryptPassword) {
        this.cryptPassword = cryptPassword;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.PASS;
    }

    @Override
    public void parseAndSetArgumentsFromString(String arguments) {
        if (arguments.length() != 32) {
            throw new Pop3UnknownCommand("Unknown Command");
        }
        String preparedArguments = arguments.trim();
        this.cryptPassword = preparedArguments;
    }
}
