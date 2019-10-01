package HealthHelper;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

public class Email {

    public static void sendMail (String adress, String text, String id) throws MessagingException, IOException{
        final Properties properties = new Properties();
        properties.load(Email.class.getResourceAsStream("mail.properties"));
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("mailhealthhealper@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(adress));
        message.setSubject("ID = " + id + ", results :");
        message.setText(text);
        Transport tr = mailSession.getTransport();
        tr.connect(null, "H123456789h");
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }
}
