package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandType;

public class Pop3CommandList implements Pop3Command {

    @Getter
    private final Integer limit;

    public Pop3CommandList(Integer limit) {
        this.limit = limit;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.LIST;
    }
}
