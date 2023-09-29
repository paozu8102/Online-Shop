/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBcontext;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author LINH
 */
public class Mail {

    public static boolean sendMail(String to, String sub, String text) {
        final String from = "nguyenhuyhoang230212a5@gmail.com";
        final String pass = "qwrx fqyk psse vzfe";

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        };
        // tao phien lam viec
        Session session = Session.getInstance(props, auth);
        // tao tin nhan
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UtF-8");
            // nguoi gui
            msg.setFrom(from);
            // nguoi nhan
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            // tieu de
            msg.setSubject(sub);

            // noi dung
            msg.setText(text);
            Transport.send(msg);
            return true;
        } catch (MessagingException ex) {
//            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println( sendMail("ngolinh09032002@gmail.com", "hello", "12345"));
    }
}
