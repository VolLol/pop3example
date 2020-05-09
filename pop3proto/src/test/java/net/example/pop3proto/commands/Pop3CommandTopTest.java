package net.example.pop3proto.commands;

import net.example.pop3proto.Pop3CommandParser;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;
import org.junit.Assert;
import org.junit.Test;

public class Pop3CommandTopTest {

    @Test
    public void correctCommand1() {

        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandTop pop3CommandTop = new Pop3CommandTop(1, 2);
        Pop3Command pop3Command = pop3CommandParser.parse("Top 1 2");


        Assert.assertEquals(pop3CommandTop.getCommandType(), pop3Command.getCommandType());
    }

    @Test
    public void correctCommand2() {

        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandTop pop3CommandTop = new Pop3CommandTop(31, 22);
        Pop3Command pop3Command = pop3CommandParser.parse("top 31 22");


        Assert.assertEquals(pop3CommandTop.getCommandType(), pop3Command.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommand() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("toop");
    }
}
