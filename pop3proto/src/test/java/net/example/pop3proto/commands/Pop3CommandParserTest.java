package net.example.pop3proto.commands;

import net.example.pop3proto.Pop3CommandParser;
import net.example.pop3proto.exceptions.Pop3UnknownCommand;
import org.junit.Assert;
import org.junit.Test;

public class Pop3CommandParserTest {
    @Test
    public void correctCommandNoop() {
        Pop3CommandNoop pop3CommandNoop = new Pop3CommandNoop();
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3CommandNoopResult = pop3CommandParser.parse("NOOP");

        Assert.assertSame(pop3CommandNoop.getCommandType(), pop3CommandNoopResult.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommandNoop() {
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

    @Test
    public void correctCommandApop() {
        Pop3CommandApop pop3CommandApop = new Pop3CommandApop("user1", "7c6a180b36896a0a8c02787eeafb0e4c");
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3Command = pop3CommandParser.parse("apop user1 7c6a180b36896a0a8c02787eeafb0e4c");

        Assert.assertEquals(pop3CommandApop.getCommandType(), pop3Command.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommandApop() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3Command = pop3CommandParser.parse("apop user1 7c6a180jdjjdjdb36896sdsdsa0a8c02787eeafb0e4c");
    }

    @Test
    public void correctCommandList1() {
        Pop3CommandList pop3CommandList = new Pop3CommandList(2);
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3Command = pop3CommandParser.parse("list 2");

        Assert.assertEquals(pop3CommandList.getCommandType(), pop3Command.getCommandType());
    }

    @Test
    public void correctCommandList2() {
        Pop3CommandList pop3CommandList = new Pop3CommandList(2);
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3Command = pop3CommandParser.parse("list 0");

        Assert.assertEquals(pop3CommandList.getCommandType(), pop3Command.getCommandType());
    }


    @Test
    public void correctCommandList3() {
        Pop3CommandList pop3CommandList = new Pop3CommandList(2);
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3Command = pop3CommandParser.parse("list");

        Assert.assertEquals(pop3CommandList.getCommandType(), pop3Command.getCommandType());
    }

    @Test
    public void correctCommandDele1() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandDele pop3CommandDele = new Pop3CommandDele(5);
        Pop3Command pop3Command = pop3CommandParser.parse("dele 5");

        Assert.assertEquals(pop3CommandDele.getCommandType(), pop3Command.getCommandType());
    }

    @Test
    public void correctCommandDele2() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3CommandDele pop3CommandDele = new Pop3CommandDele(2);
        Pop3Command pop3Command = pop3CommandParser.parse("DELE 2");

        Assert.assertEquals(pop3CommandDele.getCommandType(), pop3Command.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommandDele() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("dele");
    }

    @Test
    public void correctCommandRset() {
        Pop3CommandRset pop3Command = new Pop3CommandRset();
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3CommandResult = pop3CommandParser.parse("rset");

        Assert.assertSame(pop3Command.getCommandType(), pop3CommandResult.getCommandType());
    }

    @Test
    public void correctCommandUser() {
        Pop3CommandUser pop3Command = new Pop3CommandUser("user1");
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3CommandResult = pop3CommandParser.parse("user user1");

        Assert.assertSame(pop3Command.getCommandType(), pop3CommandResult.getCommandType());
    }

    @Test
    public void correctCommandPass() {
        Pop3CommandPass pop3Command = new Pop3CommandPass("7c6a180b36896a0a8c02787eeafb0e4c");
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3CommandResult = pop3CommandParser.parse("pass 7c6a180b36896a0a8c02787eeafb0e4c");

        Assert.assertSame(pop3Command.getCommandType(), pop3CommandResult.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommandPass() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("pass 7c6a180b3689jksdjuisdfphsdj;fwosjc;6a0a8c02787eeafb0e4c");

    }

    @Test
    public void correctCommandQuit() {
        Pop3CommandQuit pop3Command = new Pop3CommandQuit();
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3CommandResult = pop3CommandParser.parse("quit");

        Assert.assertSame(pop3Command.getCommandType(), pop3CommandResult.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommandQuit() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("quittt");
    }

    @Test
    public void correctCommandStat() {
        Pop3CommandStat pop3Command = new Pop3CommandStat();
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        Pop3Command pop3CommandResult = pop3CommandParser.parse("STAT");

        Assert.assertSame(pop3Command.getCommandType(), pop3CommandResult.getCommandType());
    }

    @Test(expected = Pop3UnknownCommand.class)
    public void incorrectCommandStat() {
        Pop3CommandParser pop3CommandParser = new Pop3CommandParser();
        pop3CommandParser.parse("statusS");
    }
}
