package net.example.pop3proto;

import net.example.pop3proto.commands.*;
import net.example.pop3proto.exceptions.Pop3CommandInvalidSyntax;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pop3CommandParser {

    final static Pattern noopCommandPattern = Pattern.compile("^(?i)(noop)(?-i)$");

    public static Pop3Command parse(String line) {
        Matcher m = noopCommandPattern.matcher(line);
        if (m.matches()) {
            return new Pop3CommandNoop();
        }

        throw new Pop3UnknownCommand("Unknown Command");
    }
}
