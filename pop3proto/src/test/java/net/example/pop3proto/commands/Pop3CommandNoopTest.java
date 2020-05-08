package net.example.pop3proto.commands;

import net.example.pop3proto.Pop3CommandParser;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;
import org.junit.Assert;
import org.junit.Test;

public class Pop3CommandNoopTest {


    @Test
    public void correctCommand1() {
        Pop3CommandNoop pop3CommandNoop = new Pop3CommandNoop();
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3CommandNoopResult = pop3CommandParser.parse("noop");

        Assert.assertSame(pop3CommandNoop.getCommandType(), pop3CommandNoopResult.getCommandType());
    }

    @Test
    public void correctCommand2() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandNoop pop3CommandNoop = new Pop3CommandNoop();

        Pop3Command pop3CommandNoopResult = pop3CommandParser.parse("NOOP");

        Assert.assertSame(pop3CommandNoop.getCommandType(), pop3CommandNoopResult.getCommandType());
    }

    @Test
    public void correctCommand3() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandNoop pop3CommandNoop = new Pop3CommandNoop();

        Pop3Command pop3CommandNoopResult = pop3CommandParser.parse("Noop");

        Assert.assertSame(pop3CommandNoop.getCommandType(), pop3CommandNoopResult.getCommandType());
    }

    @Test
    public void correctCommand4() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandNoop pop3CommandNoop = new Pop3CommandNoop();

        Pop3Command pop3CommandNoopResult = pop3CommandParser.parse("nOOp");

        Assert.assertSame(pop3CommandNoop.getCommandType(), pop3CommandNoopResult.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void inCorrectCommand() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("nop");
    }
}
