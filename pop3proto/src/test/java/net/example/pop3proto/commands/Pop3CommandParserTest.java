package net.example.pop3proto.commands;

import net.example.pop3proto.Pop3CommandParser;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;
import org.junit.Assert;
import org.junit.Test;

public class Pop3CommandParserTest {
    @Test
    public void correctCommandNoop1() {
        Pop3CommandNoop pop3CommandNoop = new Pop3CommandNoop();
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3CommandNoopResult = pop3CommandParser.parse("noop");

        Assert.assertSame(pop3CommandNoop.getCommandType(), pop3CommandNoopResult.getCommandType());
    }

    @Test
    public void correctCommandNoop2() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandNoop pop3CommandNoop = new Pop3CommandNoop();

        Pop3Command pop3CommandNoopResult = pop3CommandParser.parse("NOOP");

        Assert.assertSame(pop3CommandNoop.getCommandType(), pop3CommandNoopResult.getCommandType());
    }

    @Test
    public void correctCommandNoop3() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandNoop pop3CommandNoop = new Pop3CommandNoop();

        Pop3Command pop3CommandNoopResult = pop3CommandParser.parse("Noop");

        Assert.assertSame(pop3CommandNoop.getCommandType(), pop3CommandNoopResult.getCommandType());
    }

    @Test
    public void correctCommandNoop4() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandNoop pop3CommandNoop = new Pop3CommandNoop();

        Pop3Command pop3CommandNoopResult = pop3CommandParser.parse("nOOp");

        Assert.assertSame(pop3CommandNoop.getCommandType(), pop3CommandNoopResult.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void inCorrectCommandNoop() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("nop");
    }

    @Test
    public void correctCommandTop1() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandTop pop3CommandTop = new Pop3CommandTop(1, 2);
        Pop3Command pop3Command = pop3CommandParser.parse("Top 1 2");


        Assert.assertEquals(pop3CommandTop.getCommandType(), pop3Command.getCommandType());
    }

    @Test
    public void correctCommandTop2() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandTop pop3CommandTop = new Pop3CommandTop(31, 22);
        Pop3Command pop3Command = pop3CommandParser.parse("top 31 22");


        Assert.assertEquals(pop3CommandTop.getCommandType(), pop3Command.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommandTop() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("toop");
    }

    @Test
    public void correctCommandRetr() {
        Pop3CommandRetr pop3CommandRetr = new Pop3CommandRetr(1);
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command actual = pop3CommandParser.parse("retr 1");

        Assert.assertEquals(pop3CommandRetr.getCommandType(), actual.getCommandType());
    }


    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommandRetr() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("retr");
    }
}
