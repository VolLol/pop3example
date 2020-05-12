package net.example.server.repositories;

import java.util.LinkedList;
import java.util.List;

public class MailBoxRepository {

    private final List<MailEntity> mailsInMailBox;

    public MailBoxRepository() {
        mailsInMailBox = new LinkedList<>();
        mailsInMailBox.add(MailEntity.builder()
                .subject("Sample message 1")
                .from("Author <author@example.com>")
                .to("Recipient <recipient@example.com>")
                .payload("Notice how each subproject is prefixed in the output, so that you know which task from which project is being executed.\n"
                        + "Also note that Gradle does not process all tasks from one subproject before moving onto another.\n")
                .id(1)
                .build());
        mailsInMailBox.add(MailEntity.builder()
                .subject("Sample message 2")
                .from("Author <author@example.com>")
                .to("Recipient <recipient@example.com>")
                .payload("Notice how each subproject is prefixed in the output, so that you know which task from which project is being executed.\n"
                        + "Also note that Gradle does not process all tasks from one subproject before moving onto another.\n")
                .id(2)
                .build());
        mailsInMailBox.add(MailEntity.builder()
                .subject("Sample message 3")
                .from("Author <author@example.com>")
                .to("Recipient <recipient@example.com>")
                .payload("Notice how each subproject is prefixed in the output, so that you know which task from which project is being executed.\n"
                        + "Also note that Gradle does not process all tasks from one subproject before moving onto another.\n")
                .id(3)
                .build());
        mailsInMailBox.add(MailEntity.builder()
                .subject("Sample message 4")
                .from("Author <author@example.com>")
                .to("Recipient <recipient@example.com>")
                .payload("Notice how each subproject is prefixed in the output, so that you know which task from which project is being executed.\n"
                        + "Also note that Gradle does not process all tasks from one subproject before moving onto another.\n")
                .id(4)
                .build());
        mailsInMailBox.add(MailEntity.builder()
                .subject("Sample message 5")
                .from("Author <author@example.com>")
                .to("Recipient <recipient@example.com>")
                .payload("Notice how each subproject is prefixed in the output, so that you know which task from which project is being executed.\n"
                        + "Also note that Gradle does not process all tasks from one subproject before moving onto another.\n")
                .id(5)
                .build());
        mailsInMailBox.add(MailEntity.builder()
                .subject("Sample message 6")
                .from("Author <author@example.com>")
                .to("Recipient <recipient@example.com>")
                .payload("Notice how each subproject is prefixed in the output, so that you know which task from which project is being executed.\n"
                        + "Also note that Gradle does not process all tasks from one subproject before moving onto another.\n"
                        + "Add a test to ensure your code in the application itself works.\n"
                        + " As Spock Framework is a popular approach to testing Java code as well,\n "
                        + "create the test by first adding the Groovy plugin to the build script of the greeter sub-project. \n"
                        + "This requires the groovy plugin, and that includes the java plugin, so you can replace the word java with groovy as shown.\n")
                .id(6)
                .build());
    }

    public List<MailEntity> list() {
        return mailsInMailBox;
    }

    public void delete(int index) {
        mailsInMailBox.get(index).setDeleted(true);
    }

    public MailEntity get(int index) {
        return mailsInMailBox.get(index);
    }
}
