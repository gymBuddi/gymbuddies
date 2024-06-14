package dev.example.kinect.service.serviceImp;

import dev.example.kinect.exception.ProfileNotFoundException;
import dev.example.kinect.service.EmailService;
import dev.example.kinect.service.ProfileService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {
    private final JavaMailSender emailSender;
    private final ProfileService profileService;
    @Value("${spring.mail.username}")
    private String senderEmail;
    public EmailServiceImp(JavaMailSender emailSender, ProfileService profileService){
        this.emailSender = emailSender;
        this.profileService = profileService;
    };


    @Override
    public void sendEmail(Long profileId, String subject, String message) throws ProfileNotFoundException {
        // Retrieve profile email address
        String recipientEmail = profileService.getProfileEmail(profileId);
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            mimeMessage.setFrom(); // Replace with your sender email
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message, "UTF-8"); // Set content type and encoding
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            // Handle email sending exceptions
            throw new RuntimeException(e);
        }
    }
}
