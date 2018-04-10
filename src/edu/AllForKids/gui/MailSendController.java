/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;


import edu.AllForKids.entities.User;
import edu.AllForKids.services.CrudBabysitter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.internet.AddressException;


public class MailSendController implements Initializable {

    @FXML
    private TextField destbtn;
    @FXML
    private TextArea messagebtn;
    @FXML
    private Button sendbtn;
    public static User CurrentUser;
    CrudBabysitter CB = new CrudBabysitter();
    int babysitter ;
   

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BabysitterListeController BC = new BabysitterListeController();
    
    babysitter = BC.babysit;
       
        
        
        
    }

    @FXML
    private void SendMessage(ActionEvent event) {
        String mailEtudiant = CB.findById(babysitter).getEmail();
        System.out.println(mailEtudiant);
        
        
        String Text = messagebtn.getText();
        final String username = "piallforkids@gmail.com";
        final String password = "AZERTY123";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
       
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {

            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("piallforkids@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(mailEtudiant));
            message.setSubject("Demande");
           
            message.setText(Text);
         
          
            Transport.send(message);
  
            System.out.println("Mail sent succesfully!");

        } catch (MessagingException e) {
              System.out.println("nnn");
            throw new RuntimeException(e);
        }
    }

      
    
}
