package net.example.pop3proto;

import net.example.pop3proto.commands.*;
import net.example.pop3proto.exceptions.Pop3CommandInvalidSyntax;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pop3CommandParser {

    final private static Pattern noopCommandPattern = Pattern.compile("^(?i)(noop)(?-i)$");
    final private static Pattern retrCommandPattern = Pattern.compile("^(?i)retr(?-i)\\s(\\d+)$");
    final private static Pattern topCommandPattern = Pattern.compile("^(?i)top(?-i)\\s(\\d+)\\s(\\d+)$");
    final private static Pattern apopCommandPattern = Pattern.compile("^(?i)apop(?-i)\\s([a-z0-9A-Z]+)\\s([a-z0-9]{32})$");
    final private static Pattern listCommandPattern = Pattern.compile("^list\\s?(\\d+|)$");
    final private static Pattern deleCommandPattern = Pattern.compile("^(?i)dele(?-i)\\s(\\d)");

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
        if (m.matches()) {
            int mailIndex = Integer.parseInt(m.group(1));
            int countLines = Integer.parseInt(m.group(2));
            return new Pop3CommandTop(mailIndex, countLines);
        }

        m = apopCommandPattern.matcher(line);
        if (m.matches()) {
            String username = m.group(1);
            String cryptPassword = m.group(2);
            return new Pop3CommandApop(username, cryptPassword);
        }

        m = listCommandPattern.matcher(line);
        if (m.matches()) {
            try {
                Integer limit = Integer.parseInt(m.group(1));
                return new Pop3CommandList(limit);
            } catch (NumberFormatException e) {
                return new Pop3CommandList(0);
            }
        }

        m = deleCommandPattern.matcher(line);
        if (m.matches()) {
            int mailIndex = Integer.parseInt(m.group(1));
            return new Pop3CommandDele(mailIndex);
        }
        throw new Pop3UnknownCommand("Unknown Command");
    }

}
