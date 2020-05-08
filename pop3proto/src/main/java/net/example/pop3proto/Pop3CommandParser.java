package net.example.pop3proto;

import net.example.pop3proto.commands.*;
import net.example.pop3proto.exceptions.Pop3CommandInvalidSyntax;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pop3CommandParser {

    final private static Pattern noopCommandPattern = Pattern.compile("^(?i)(noop)(?-i)$");
    final private static Pattern retrCommandPattern = Pattern.compile("^(?i)Retr(?-i)\\s(\\d+)$");

    public static Pop3Command parse(String line) {
        Matcher m = retrCommandPattern.matcher(line);
        if (chekForNoopCommand(line)) {
            return new Pop3CommandNoop();
        } else if (m.matches()) {
            int mailIndex = Integer.parseInt(m.group(1));
            return new Pop3CommandRetr(mailIndex);
        }

        throw new Pop3UnknownCommand("Unknown Command");
    }

    private static boolean chekForNoopCommand(String line) {
        Matcher m = noopCommandPattern.matcher(line);
        return m.matches();
    }

    private static boolean checkForRetrCommand(String line) {
        Matcher m = retrCommandPattern.matcher(line);
        return m.matches();
    }
}
