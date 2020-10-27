package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandType;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;

public class Pop3CommandApop implements Pop3Command {

    @Getter
    private String cryptPassword;
    @Getter
    private String username;

    public Pop3CommandApop() {
    }

    public Pop3CommandApop(String username, String cryptPassword) {
        this.cryptPassword = cryptPassword;
        this.username = username;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.APOP;
    }

    @Override
    public void parseAndSetArgumentsFromString(String arguments) {
        String argumentsPrepared = arguments.trim();
        int spaceIndex = argumentsPrepared.indexOf(" ");
        if (spaceIndex == -1) {
            throw new Pop3UnknownCommand("Unknown Command");
        }
        this.username = argumentsPrepared.substring(0, spaceIndex);
        String secondsArgumentPrepared = argumentsPrepared.substring(spaceIndex + 1).trim();
        spaceIndex = secondsArgumentPrepared.indexOf(" ");
        if (secondsArgumentPrepared.equals("") || secondsArgumentPrepared.length() > 32) {
            throw new Pop3UnknownCommand("Unknown Command");
        }
        if (spaceIndex != -1) {
            this.cryptPassword = secondsArgumentPrepared.substring(0, spaceIndex);
        } else {
            this.cryptPassword = secondsArgumentPrepared;
        }

    }
}
