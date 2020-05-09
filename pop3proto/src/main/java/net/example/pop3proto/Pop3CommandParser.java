package net.example.pop3proto;

import net.example.pop3proto.commands.*;
import net.example.pop3proto.exceptions.Pop3CommandInvalidSyntax;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pop3CommandParser {

    final private static Pattern noopCommandPattern = Pattern.compile("^(?i)(noop)(?-i)$");
    final private static Pattern retrCommandPattern = Pattern.compile("^(?i)Retr(?-i)\\s(\\d+)$");
    final private static Pattern topCommandPattern = Pattern.compile("^(?i)Top(?-i)\\s(\\d+)\\s(\\d+)$");

    public static Pop3Command parse(String line) {
        Matcher m = noopCommandPattern.matcher(line);
        if (m.matches()) {
            return new Pop3CommandNoop();
        }
        m = retrCommandPattern.matcher(line);
        if (m.matches()) {
            int mailIndex = Integer.parseInt(m.group(1));
            return new Pop3CommandRetr(mailIndex);
        }
        m = topCommandPattern.matcher(line);
        if (m.usePattern(topCommandPattern).matches()) {
            int mailIndex = Integer.parseInt(m.group(1));
            int countLines = Integer.parseInt(m.group(2));
            return new Pop3CommandTop(mailIndex, countLines);
        }

        throw new Pop3UnknownCommand("Unknown Command");
    }

}
