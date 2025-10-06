package co.edu.uniquindio.application.service.interfaces;

import co.edu.uniquindio.application.dto.EmailDTO;

public interface EmailService {
    void sendMail(EmailDTO emailDTO) throws Exception;
}
