package net.example.pop3proto.commands;

import net.example.pop3proto.Pop3CommandType;

public interface Pop3Command {
    public Pop3CommandType getCommandType();
}
