package net.example.pop3proto;

import net.example.pop3proto.commands.*;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pop3CommandParser {

    private static final HashMap<String, Pop3Command> COMMAND_MAP = new HashMap<>();

    static {
        COMMAND_MAP.put("APOP", new Pop3CommandApop());
        COMMAND_MAP.put("DELE", new Pop3CommandDele());
        COMMAND_MAP.put("LIST", new Pop3CommandList());
        COMMAND_MAP.put("NOOP", new Pop3CommandNoop());
        COMMAND_MAP.put("PASS", new Pop3CommandPass());
        COMMAND_MAP.put("QUIT", new Pop3CommandQuit());
        COMMAND_MAP.put("RETR", new Pop3CommandRetr());
        COMMAND_MAP.put("RSET", new Pop3CommandRset());
        COMMAND_MAP.put("STAT", new Pop3CommandStat());
        COMMAND_MAP.put("TOP", new Pop3CommandTop());
        COMMAND_MAP.put("USER", new Pop3CommandUser());
    }

    public static Pop3Command parse(String line) {
        String preparedLine = line.trim();
        int spaceIndex = preparedLine.indexOf(" ");
        String commandName = null;
        String argumentsLine = null;
        if (spaceIndex != -1) {
            argumentsLine = preparedLine.substring(spaceIndex + 1);
            commandName = preparedLine.substring(0, spaceIndex).toUpperCase();
        } else {
            commandName = preparedLine.toUpperCase();
        }

        Pop3Command pop3Command = COMMAND_MAP.get(commandName);
        if (pop3Command == null) {
            throw new Pop3UnknownCommand("Unknown Command");
        }

        pop3Command.parseAndSetArgumentsFromString(argumentsLine);
        return pop3Command;
    }
}
