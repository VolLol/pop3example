package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandType;

public class Pop3CommandDele implements Pop3Command {

    @Getter
    private final int mailIndex;

    public Pop3CommandDele(int mailIndex) {
        this.mailIndex = mailIndex;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.DELE;
    }
}
