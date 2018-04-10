/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Offre;
import edu.AllForKids.entities.User;
import static edu.AllForKids.gui.AddOffreController.CurrentUser;
import static edu.AllForKids.gui.AfficheParentOffreController.off;
import static edu.AllForKids.gui.BabysitterListeController.babysit;
import edu.AllForKids.services.CrudBabysitter;
import edu.AllForKids.services.CrudOffre;
import edu.AllForKids.services.CrudUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProfileBabyController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label sexe;
    @FXML
    private Label etat;
    @FXML
    private Label ville;
    @FXML
    private Label adresse;
    @FXML
    private Label nbrann;
    @FXML
    private Label age;
    @FXML
    private Label email;
    @FXML
    private Label numtel;
    
    public static User CurrentUser;
    CrudUser the_user = new CrudUser();
    CrudBabysitter baby = new CrudBabysitter();
    @FXML
    private ImageView tof;
    @FXML
    private Button updateprofile;
    @FXML
    private Text textage;
    @FXML
    private Text textetat;
    @FXML
    private Text textville;
    @FXML
    private Text textnum;
    @FXML
    private Text textnbr;
    @FXML
    private Text textadr;
    User u = null;
    public static User utest ;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginController lg =new LoginController();
        CurrentUser = lg.CurrentUser;
       User u;
       etat.setVisible(false);
       ville.setVisible(false);
       adresse.setVisible(false);
       nbrann.setVisible(false);
       age.setVisible(false);
       numtel.setVisible(false);
       textage.setVisible(false);
       textetat.setVisible(false);
       textville.setVisible(false);
       textnum.setVisible(false);
       textnbr.setVisible(false);
       textadr.setVisible(false);
        try {
            u = the_user.findById(CurrentUser.getId());
       
       username.setText(u.getUsername());
       sexe.setText(u.getSexe());
       email.setText(u.getEmail());
       File file = new File("C:\\wamp\\www\\PI4\\PI4\\web\\images\\"+u.getNom_image());
        System.out.println(u.getNom_image());
        Image image = new Image(file.toURI().toString());
        tof.setImage(image);
        utest=u;
       if (u.getRoles().equals("a:1:{i:0;s:14:\"ROLE_BABYSITER\";}"))
       {
            etat.setVisible(true);
       ville.setVisible(true);
       adresse.setVisible(true);
       nbrann.setVisible(true);
       age.setVisible(true);
       numtel.setVisible(true);
       textage.setVisible(true);
       textetat.setVisible(true);
       textville.setVisible(true);
       textnum.setVisible(true);
       textnbr.setVisible(true);
       textadr.setVisible(true);
          User bab = baby.findById(CurrentUser.getId());
       etat.setText(bab.getEtat());
       ville.setText(bab.getVille());
       adresse.setText(bab.getAdresse()); 
       nbrann.setText(Integer.toString(bab.getNbrAnneeExp()));
       age.setText(Integer.toString(bab.getAge()));
       numtel.setText(Integer.toString(bab.getNumTe()));
       u = bab;
       utest=u;
       
       
       }
        updateprofile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                FXMLLoader loader= new FXMLLoader(getClass().getResource("updateProfil.fxml"));
            Parent root;
                try {
                    root = loader.load();
               updateprofile.getScene().setRoot(root);
             } catch (IOException ex) {
                    Logger.getLogger(BabysitterListeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
       
         } catch (SQLException ex) {
            Logger.getLogger(ProfileBabyController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      

       
       
        // TODO
    }    

    @FXML
    private void UpdateProfile(ActionEvent event) {
       
        
        try { 
            utest=u;
            
            FXMLLoader loader = new FXMLLoader (getClass().getResource("updateProfil.fxml"));
            Parent root = loader.load();
           
//            AddOffreController dpc =loader.getController(); 
//            String nbrEnf = ""+SelectedForEdit.getNbrEnfant()+"";
//            dpc.setNbrEnfantbtn(nbrEnf);
//            dpc.setServicebtn(SelectedForEdit.getService());
//            String age = ""+SelectedForEdit.getAge()+"";
//            dpc.setAgebtn(age);
            
            updateprofile.getScene().setRoot(root);
                          
                    } catch (IOException ex) {
            Logger.getLogger(AfficheParentOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
