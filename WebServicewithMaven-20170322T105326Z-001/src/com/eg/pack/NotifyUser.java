package com.eg.pack;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotifyUser {

     public NotifyUser()
     {
         System.out.println("hello");
     }
     // public static void main(String[] args) {
         public NotifyUser(String mail,String tname,String otp) {
        	 System.out.println(mail+" "+tname+" "+otp);

String host = "webmail.omfysgroup.com";
final String user = "helpdesk@omfysgroup.com";// change accordingly
final String password = "Zxcv@123";// change accordingly

String to = mail;
//String randomno1 = no;
// Get the session object
Properties props = new Properties();
props.put("mail.smtp.host", host);
props.put("mail.smtp.auth", "true");

Session session1 = Session.getDefaultInstance(props, new 
javax.mail.Authenticator() {
     protected PasswordAuthentication getPasswordAuthentication() {
         return new PasswordAuthentication(user, password);
     }
});

// Compose the message
try {
     MimeMessage message = new MimeMessage(session1);
     message.setFrom(new InternetAddress(user));
     message.addRecipient(Message.RecipientType.TO, new 
InternetAddress(to));
     message.setSubject("Notification");
     message.setContent("<h5 style='color:blue'>Hello " + tname + 
"</h5>" + "\n" + "\n "
             + "\n Your One-Time-Password is "+otp+"", "text/html");
     // send the message
     Transport.send(message);

     System.out.println("message sent successfully...");

} catch (MessagingException e) {
     e.printStackTrace();
}
}


///code to send the mail to user when admin add him

         public void sentmail(String username, String password1,String 
firstname,String server_path) {

             String host = "webmail.omfysgroup.com";
             final String user = "helpdesk@omfysgroup.com";// change accordingly
             final String password = "Zxcv@123";// change accordingly

             String to = username;
             //String randomno1 = no;
             String fname = firstname;
             // Get the session object
             Properties props = new Properties();
             props.put("mail.smtp.host", host);
             props.put("mail.smtp.auth", "true");

             Session session1 = Session.getDefaultInstance(props, new 
javax.mail.Authenticator() {
                 protected PasswordAuthentication 
getPasswordAuthentication() {
                     return new PasswordAuthentication(user, password);
                 }
             });

             // Compose the message
             try {
                 MimeMessage message = new MimeMessage(session1);
                 message.setFrom(new InternetAddress(user));
                 message.addRecipient(Message.RecipientType.TO, new 
InternetAddress(to));
                 message.setSubject("Notification");
                 message.setContent("<h5 style='color:blue'>Hello " + 
fname + "</h5>" + "\n" + "\n "
                         + "\n    Following are your account details to use our organization services with login page URL. Please don't share with anyone.<pre>"
                         + "\n Username : "+username+""
                         + "\n Password : "+password1+""
                         + "\n URL      : <h5 style='color:blue'>"+ 
server_path + "BASS_ADMIN/</h5></pre>", "text/html");
                 // send the message
                 Transport.send(message);

                 System.out.println("message sent successfully...");

             } catch (MessagingException e) {
                 e.printStackTrace();
             }
             }


}