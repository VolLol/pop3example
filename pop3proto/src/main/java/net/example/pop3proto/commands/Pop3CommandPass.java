package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandType;

public class Pop3CommandPass implements Pop3Command {

    @Getter
    private final String cryptPassword;


    public Pop3CommandPass(String cryptPassword) {
        this.cryptPassword = cryptPassword;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.PASS;
    }
}
