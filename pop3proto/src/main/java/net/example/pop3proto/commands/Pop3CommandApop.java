package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandType;

public class Pop3CommandApop implements Pop3Command {

    @Getter
    private final String cryptPassword;
    @Getter
    private final String username;

    public Pop3CommandApop(String username, String cryptPassword) {
        this.cryptPassword = cryptPassword;
        this.username = username;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.APOP;
    }
}
