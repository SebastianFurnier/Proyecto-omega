package com.omega.Proyecto.omega.Service;

import com.omega.Proyecto.omega.Model.EmailDTO;
import jakarta.mail.MessagingException;

public interface IEmailService {
    void sendMail(EmailDTO emailDTO) throws MessagingException;
}
