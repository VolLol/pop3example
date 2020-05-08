package net.example.pop3proto.exceptions;

public class Pop3CommandInvalidSyntax extends RuntimeException{
    public Pop3CommandInvalidSyntax(String message) {
        super(message);
    }
}
