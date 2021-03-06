/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.User;
import edu.AllForKids.services.CrudUser;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfmail;

    @FXML
    private Button btnconnexion;
    @FXML
    private Button btnsignup;
    @FXML
    private Text BtnForgetPassword;
    @FXML
    private PasswordField tfpass;
    public static User CurrentUser;

    /**
     * Initializes the controller class.
     */
    File imgfile;
    int file = 0;
    Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnconnexion(ActionEvent event) throws SQLException, IOException {

        tfmail.setStyle(" -fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white");
        tfpass.setStyle(" -fx-border-color : white ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white");

        if (tfmail.getText().equals("") || tfpass.getText().equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Veuillez inserer votre email et votre mot de pass");
            a.showAndWait();

            if (tfmail.getText().equals("")) {
                tfmail.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ");
                Alert b = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Veuillez inserer votre email ");
                a.showAndWait();
            }
            if (tfpass.getText().equals("")) {
                tfpass.setStyle("-fx-border-color : red ; -fx-border-width :  0 0 2px 0 ; -fx-background-color :  transparent ; -fx-text-fill : white ");
                Alert c = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Veuillez inserer  et votre mot de pass");
                a.showAndWait();
            }
        } else {
            String email = tfmail.getText();
            String pass = tfpass.getText();
            CrudUser crudutilisateur = new CrudUser();
            User u = crudutilisateur.Authentification(email, pass);
            System.out.println("user n'est pas null *****" + u);

            if (u == null) {
                System.out.println("iln'yapas dns base");
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Email ou mot de passe incorrect");
                a.showAndWait();
                System.out.println("mailfaut");

            } else {
                CurrentUser = u;
                System.out.println(u);
                if (u.getRoles().equals("Parent")) {
                    System.out.println("parent");

                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AcceuilFrontEnd.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else if (u.getRoles().equals("Admin")) {
                    System.out.println("admin");

                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AccueilBackEnd.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else if (u.getRoles().equals("Pediatre")) {
                    System.out.println("    ped");
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AcceuilFrontEnd.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                } else if (u.getRoles().equals("a:1:{i:0;s:14:\"ROLE_BABYSITER\";}")) {
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("AcceuilFrontEnd.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();

                }

            }
        }
    }

    @FXML
    private void Register(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("R.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        /*  primaryStage=new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("R.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
         */
    }

}
