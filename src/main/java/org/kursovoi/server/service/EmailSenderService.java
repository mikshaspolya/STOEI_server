package org.kursovoi.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kursovoi.server.dto.CreateUserDto;
import org.kursovoi.server.dto.TransactionDto;
import org.kursovoi.server.dto.mail.EmailMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSenderService {

    private final JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(EmailMessage message) {
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(message.getTo());
        mailMessage.setText(message.getBody());
        mailMessage.setSubject(message.getSub());

        sender.send(mailMessage);

        log.info("Email was sent to: " + message.getTo());
    }

    public void sendTransactionReceipt(TransactionDto transactionDto, String to) {

        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setText("Success transaction from account " + transactionDto.getIdFrom() +
                " to account " + transactionDto.getIdTo() +
                ". Transaction amount: " + transactionDto.getSum());
        mailMessage.setSubject("TRANSACTION RECEIPT");

        mailMessage.setTo(to);

        sender.send(mailMessage);

        log.info("Email was sent to: " + transactionDto.getIdTo());

    }

    public void sendConfirmationOfRegistration(CreateUserDto dto) {
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setText("Success registration for client " + dto.getUuid() + "\n" +
                "login: " + dto.getLogin() + "\n" +
                "password: " + dto.getPassword());
        mailMessage.setSubject("CLIENT REGISTRATION");

        mailMessage.setTo(dto.getEmail());

        sender.send(mailMessage);

        log.info("Email was sent to: " + dto.getEmail());
    }


}
