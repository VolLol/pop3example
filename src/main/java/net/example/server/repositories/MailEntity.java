package net.example.server.repositories;

import lombok.Builder;
import lombok.Getter;

@Builder
public class MailEntity {
    @Getter
    private final String subject;
    @Getter
    private final String from;
    @Getter
    private final String to;
    @Getter
    private final String payload;

    public MailEntity(String subject, String from, String to, String payload) {
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.payload = payload;
    }

    public String show() {
        return "From: " + from + "\r\n"
                + "To: " + to + "\r\n"
                + "Subject: " + subject + "\r\n"
                + "\r\n"
                + payload;
    }
}
