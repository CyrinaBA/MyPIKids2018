/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Offre;
import edu.AllForKids.services.CrudOffre;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ModifierOffreController implements Initializable {

    @FXML
    private TextField NbrEnfantbtn;
    @FXML
    private TextField Servicebtn;
    @FXML
    private TextField Agebtn;
    @FXML
    private ChoiceBox<String> Tempsbtn;
    @FXML
    private DatePicker Datebtn;
    @FXML
    private ChoiceBox<String> Villebtn;
    @FXML
    private Button UpdateOffrebtn;
    @FXML
    private Button ResetOffrebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> temps = FXCollections.observableArrayList("matin", "Soir", "aprés midi", "Tout au long du jour");
        Tempsbtn.setItems(temps);
        ObservableList<String> ville = FXCollections.observableArrayList("Ariana","Beja","Ben Arous"
            ,"Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine",
                "Kebili","Le Kef","Mahdia","La Manouba","Medenine",
                "Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse",
                "Tataouine","Tozeur","Tunis","Zaghouan");
        Villebtn.setItems(ville);
        AfficheParentOffreController ac = new AfficheParentOffreController();
        Offre o = ac.off;
        System.err.println(o);
        NbrEnfantbtn.setText(String.valueOf(o.getNbrEnfant()));
        Agebtn.setText(String.valueOf(o.getAge()));
        Servicebtn.setText(o.getService());
        Tempsbtn.getSelectionModel().select(o.getTemps());// ldate mchet wl ville mchet. ritha ? le xD
        Datebtn.setValue(LocalDate.parse(o.getDateOffre().toString()));
        Villebtn.getSelectionModel().select(o.getVille());
        //Let's hope this works :p hope so
    }

    @FXML
    private void Reset(ActionEvent event) {
    }

    @FXML
    private void UpdateOffre(ActionEvent event) throws SQLException {
        CrudOffre myTool = new CrudOffre();
        Offre o = new Offre();
        AfficheParentOffreController aff = new AfficheParentOffreController();
        //    int ido= aff
        o.setParent_id(14);
        o.setBabysitter_id(13);
        int nbrEnfant = Integer.parseInt(NbrEnfantbtn.getText());
        o.setNbrEnfant(nbrEnfant);
        o.setService(Servicebtn.getText());
        int age = Integer.parseInt(Agebtn.getText());
        o.setAge(age);
        o.setTemps(Tempsbtn.getValue());

        o.setDateOffre(Date.valueOf(Datebtn.getValue()));
        

        o.setAccept(0);

        o.setVille(Villebtn.getValue());

        myTool.updateOffre(o,aff.off.getId());
        JOptionPane.showMessageDialog(null, "Offre Modifié");
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficheParentOffre.fxml"));
            Parent root;

            root = loader.load();

            UpdateOffrebtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TextField getNbrEnfantbtn() {
        return NbrEnfantbtn;
    }

    public void setNbrEnfantbtn(String NbrEnfantbtn) {
        this.NbrEnfantbtn.setText(NbrEnfantbtn);
    }

    public TextField getServicebtn() {
        return Servicebtn;
    }

    public void setServicebtn(String Servicebtn) {
        this.Servicebtn.setText(Servicebtn);
    }

    public TextField getAgebtn() {
        return Agebtn;
    }

    public void setAgebtn(String Agebtn) {
        this.Agebtn.setText(Agebtn);
    }

    public ChoiceBox<String> getTempsbtn() {
        return Tempsbtn;
    }

    public void setTempsbtn(ChoiceBox<String> Tempsbtn) {
        this.Tempsbtn = Tempsbtn;
    }

    public DatePicker getDatebtn() {
        return Datebtn;
    }

    public void setDatebtn(DatePicker Datebtn) {
        this.Datebtn = Datebtn;
    }

    public ChoiceBox<String> getVillebtn() {
        return Villebtn;
    }

    public void setVillebtn(ChoiceBox<String> Villebtn) {
        this.Villebtn = Villebtn;
    }
}
