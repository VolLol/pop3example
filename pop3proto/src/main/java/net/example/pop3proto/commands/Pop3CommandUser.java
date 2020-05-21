package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandType;

public class Pop3CommandUser implements Pop3Command {

    @Getter
    private final String username;

    public Pop3CommandUser(String username) {
        this.username = username;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.USER;
    }

}
