package net.example.pop3proto.exceptions;

public class Pop3UnknownCommand extends RuntimeException {
    public Pop3UnknownCommand(String message) {
        super(message);
    }
}
