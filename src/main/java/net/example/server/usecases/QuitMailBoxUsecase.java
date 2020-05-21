package net.example.server.usecases;

import net.example.server.Pop3SessionContext;
import net.example.server.repositories.MailBoxRepository;
import net.example.server.repositories.MailEntity;

import java.util.ArrayList;
import java.util.List;

public class QuitMailBoxUsecase {
    private final Pop3SessionContext sessionContext;
    private final MailBoxRepository mailBoxRepository;

    public QuitMailBoxUsecase(Pop3SessionContext sessionContext, MailBoxRepository mailBoxRepository) {
        this.sessionContext = sessionContext;
        this.mailBoxRepository = mailBoxRepository;
    }

    public List<String> execute() {
        System.out.println("[" + sessionContext.getClientIP() + "] " + " execute QuitMailBoxUseCase");
        ArrayList<String> result = new ArrayList<>();
        if (sessionContext.getUser() != null) {
            sessionContext.setAuthenticated(false);
            sessionContext.setSessionStateNOAUTHORIZATION();

            List<MailEntity> listOfMails = mailBoxRepository.list();
            for (MailEntity mail : listOfMails) {
                if (mail.isDeleted()) {
                    mail.setDeleted(false);
                }
            }
            result.add("+OK " + sessionContext.getUser().getUsername() + " POP3 server signing off");
        } else {
            result.add("-ERR user not start work");
        }
        return result;
    }
}
