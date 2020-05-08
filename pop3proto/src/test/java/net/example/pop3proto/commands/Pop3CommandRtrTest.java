package net.example.pop3proto.commands;

import net.example.pop3proto.Pop3CommandParser;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;
import org.junit.Assert;
import org.junit.Test;

public class Pop3CommandRtrTest {

    @Test
    public void correctCommand() {
        Pop3CommandRetr pop3CommandRetr = new Pop3CommandRetr(1);
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command actual = pop3CommandParser.parse("retr 1");

        Assert.assertEquals(pop3CommandRetr.getCommandType(), actual.getCommandType());
    }


    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommand() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("retr");
    }
}
