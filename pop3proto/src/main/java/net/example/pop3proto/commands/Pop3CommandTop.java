package net.example.pop3proto.commands;

import lombok.Getter;
import net.example.pop3proto.Pop3CommandType;

public class Pop3CommandTop implements Pop3Command {

    @Getter
    private int mailIndex;
    @Getter
    private int countLines;

    public Pop3CommandTop() {
    }

    public Pop3CommandTop(int mailIndex, int countLines) {
        this.mailIndex = mailIndex;
        this.countLines = countLines;
    }

    @Override
    public Pop3CommandType getCommandType() {
        return Pop3CommandType.TOP;
    }

    @Override
    public void parseAndSetArgumentsFromString(String arguments) {

    }
}
