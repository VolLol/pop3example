package net.example.server.repositories;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

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

    @Getter
    @Setter
    private boolean isDeleted;

    @Getter
    private Integer id;

    public MailEntity(String subject, String from, String to, String payload, boolean isDeleted, Integer id) {
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.payload = payload;
        this.isDeleted = false;
        this.id = id;
    }

    public String show() {
        return "From: " + from + "\r\n"
                + "To: " + to + "\r\n"
                + "Subject: " + subject + "\r\n"
                + "\r\n"
                + payload;
    }
}
