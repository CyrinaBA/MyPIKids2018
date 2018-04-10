/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Offre;
import edu.AllForKids.entities.User;
import static edu.AllForKids.gui.ProfileBabyController.CurrentUser;
import edu.AllForKids.services.CrudBabysitter;
import edu.AllForKids.services.CrudOffre;
import edu.AllForKids.services.CrudUser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateProfilController implements Initializable {

    @FXML
    private ImageView tof;
    @FXML
    private Text textadr;
    @FXML
    private Text textetat;
    @FXML
    private Text textville;
    @FXML
    private Text textnum;
    @FXML
    private Text textnbr;
    @FXML
    private Text textage;
    @FXML
    private Button saveprofil;
    @FXML
    private TextField sexe;
    @FXML
    private TextField mail;
    @FXML
    private TextField passwrd1;
    @FXML
    private TextField passwrd2;
    @FXML
    private TextField adr;
    @FXML
    private TextField etat;
    @FXML
    private TextField age;
    @FXML
    private ChoiceBox<String> ville;
    @FXML
    private TextField nbr;
    @FXML
    private TextField num;
    @FXML
    private TextField nom;
    ProfileBabyController PUser ;
    CrudUser the_user = new CrudUser();
    CrudBabysitter baby = new CrudBabysitter();
    String path1 = "";
    File file1;
    File source;
    File dest;
     private FileChooser fileChooser = new FileChooser();
    @FXML
    private Button choose;

    Stage primarystage;
    User o;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginController lg =new LoginController();
        CurrentUser = lg.CurrentUser;
        User o = PUser.utest;
        System.err.println(o); 
        etat.setVisible(false);
       ville.setVisible(false);
       adr.setVisible(false);
       nbr.setVisible(false);
       age.setVisible(false);
       num.setVisible(false);
       textage.setVisible(false);
       textetat.setVisible(false);
       textville.setVisible(false);
       textnum.setVisible(false);
       textnbr.setVisible(false);
       textadr.setVisible(false);
        try {
          User u = the_user.findById(o.getId());
       
       nom.setText(u.getUsername());
       sexe.setText(u.getSexe());
       mail.setText(u.getEmail());
       File file = new File("C:\\wamp\\www\\PI4\\PI4\\web\\images\\"+u.getNom_image());
        System.out.println(u.getNom_image());
        Image image = new Image(file.toURI().toString());
        tof.setImage(image);
        
       if (u.getRoles().equals("a:1:{i:0;s:14:\"ROLE_BABYSITER\";}"))
       {
            etat.setVisible(true);
       ville.setVisible(true);
       adr.setVisible(true);
       nbr.setVisible(true);
       age.setVisible(true);
       num.setVisible(true);
       textage.setVisible(true);
       textetat.setVisible(true);
       textville.setVisible(true);
       textnum.setVisible(true);
       textnbr.setVisible(true);
       textadr.setVisible(true);
          User bab = baby.findById(o.getId());
       etat.setText(bab.getEtat());
               ObservableList<String> v = FXCollections.observableArrayList("Ariana","Beja","Ben Arous"
            ,"Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine",
                "Kebili","Le Kef","Mahdia","La Manouba","Medenine",
                "Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse",
                "Tataouine","Tozeur","Tunis","Zaghouan");
        ville.setItems(v);
       ville.getSelectionModel().select(o.getVille());
       adr.setText(bab.getAdresse()); 
       nbr.setText(Integer.toString(bab.getNbrAnneeExp()));
       age.setText(Integer.toString(bab.getAge()));
       num.setText(Integer.toString(bab.getNumTe()));
       u = bab;
       
       }
         } catch (SQLException ex) {
            Logger.getLogger(ProfileBabyController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    

    @FXML
    private void saveprofil(ActionEvent event) throws SQLException {
         CrudUser myUser = new CrudUser();
        User o = new User();
        ProfileBabyController profil = new ProfileBabyController();
        //    int ido= aff
        o.setSexe(sexe.getText());
        o.setEmail(mail.getText());
        o.setUsername(nom.getText());
        o.setPassword(passwrd1.getText());
        o.setNom_image(path1);
        myUser.updateUser(o,PUser.utest.getId());
        if (etat.visibleProperty().equals(true))
        {
            o.setVille(ville.getValue());
            o.setAdresse(adr.getText());     
            o.setEtat(etat.getText());     
        int Age = Integer.parseInt(age.getText());
        o.setAge(Age);
        int nbrExp = Integer.parseInt(nbr.getText());
        o.setNbrAnneeExp(nbrExp);
        int numtel = Integer.parseInt(num.getText());
        o.setAge(numtel);
        myUser.updateUserBB(o,PUser.u.getId());
        
        }
        
        JOptionPane.showMessageDialog(null, "Profile Modifié");
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileBaby.fxml"));
            Parent root;

            root = loader.load();

            saveprofil.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    @FXML
    private void choisir(ActionEvent event) {
        
        File file = fileChooser.showOpenDialog(primarystage);
        if (file != null) {
            if (file.getName().contains(".jpg")) {
                System.out.println("khtart fichier");

                System.out.println(UUID.randomUUID().toString().concat(".jpg"));

                String uuid = UUID.randomUUID().toString().concat(".jpg");

                path1 = file.getAbsolutePath();
                System.out.println(path1);
                //String pathxx1 = file.toURI().toURL().toString();

                //Image image = new Image(pathxx1);
                file1 = new File(path1);

                Image image1 = new Image(file1.toURI().toString());
                tof.setImage(image1);
                source = new File(path1);

                dest = new File("C:\\wamp\\www\\PI4\\PI4\\web\\images\\" + uuid);
                path1 = uuid;

            }

        }
    }
    
}
