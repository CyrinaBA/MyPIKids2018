/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Avis;
import edu.AllForKids.entities.User;
import static edu.AllForKids.gui.AvisParentController.CurrentUser;
import static edu.AllForKids.gui.AvisParentController.babysit;
import edu.AllForKids.services.CrudAvis;
import edu.AllForKids.services.CrudBabysitter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class BabysitterListeController implements Initializable {

    @FXML
    private TextField Titre;
    @FXML
    private ScrollPane ListeBaby;
    @FXML
    private Button ChercherBaby;
    @FXML
    private Button chercherAge;
    
    CrudBabysitter CB = new CrudBabysitter();
    CrudAvis CA = new CrudAvis();
    public static User CurrentUser;
    
    public static int babysit;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListeBaby.setStyle("-fx-background-color: white");
        LoginController lg =new LoginController();
        CurrentUser = lg.CurrentUser;
         String[] possibleWords = {"Ariana","Beja","Ben Arous"
            ,"Bizerte","Gabes","Gafsa","Jendouba","Kairouan","Kasserine",
                "Kebili","Le Kef","Mahdia","La Manouba","Medenine",
                "Monastir","Nabeul","Sfax","Sidi Bouzid","Siliana","Sousse",
                "Tataouine","Tozeur","Tunis","Zaghouan"};
        TextFields.bindAutoCompletion(Titre, possibleWords);
         try {
            ListeBaby.setContent(ListofAllBaby());

        } catch (SQLException ex) {

        }
        
      
        // TODO
    }

     public Node ListofAllBaby() throws SQLException {

        VBox root0 = new VBox(10);
        HBox H0 = new HBox(6);
        VBox V2 = new VBox(10);

        ObservableList<User> OB = FXCollections.observableArrayList();
        OB = (ObservableList<User>) CB.displayBabysitter();

        for (int i = 0; i < OB.size(); i++) {

            HBox root = new HBox(10);
            root.setAlignment(Pos.CENTER_LEFT);
            root.setStyle("-fx-Border-color:  #2471A3");
            root.setPadding(new Insets(5, 5, 5, 5));
            root.setStyle("-fx-background-color: white");
            try {
                User u = OB.get(i);
               

                //affiche image
                File file = new File("C:\\wamp\\www\\PI4\\PI4\\web\\images\\"+u.getNom_image());
                System.out.println(u.getNom_image());
                  Image image = new Image(file.toURI().toString());
                
                ImageView IMG = new ImageView(image);
                IMG.fitHeightProperty().set(200);
                IMG.fitWidthProperty().set(200);
                IMG.preserveRatioProperty().set(true);
                Separator sep = new Separator(Orientation.VERTICAL);
                VBox root2 = new VBox(6);
                root2.prefWidthProperty().set(1000);
                root2.prefHeightProperty().set(200);
                root2.setPadding(new Insets(4, 4, 4, 4));
                root2.setStyle("-fx-background-color: #1FB1C8");

                
                // on click image 
                IMG.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println(u.getId());
                        babysit = u.getId();
                        try {
            ListeBaby.setContent(ListofAvis(babysit));

        } catch (SQLException ex) {

        }
                       /* babysit = u.getId() ;
                        FXMLLoader loader= new FXMLLoader(getClass().getResource("AvisParent.fxml"));
            Parent root;
                try {
                    root = loader.load();
                IMG.getScene().setRoot(root);
             } catch (IOException ex) {
                    Logger.getLogger(BabysitterListeController.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                        
                    }
                });
                //les information des babysitter
                //*****Username
                Label Username = new Label(u.getUsername());
                Username.setFont(new Font("Arial", 30));
                Username.setTextFill(Color.web("#17202A"));
                //*******description
                Label e = new Label(u.getEtat());
                e.backgroundProperty().set(Background.EMPTY);
                e.setTextFill(Color.web("#17202A"));
                VBox root3 = new VBox(3);
                Separator sep2 = new Separator(Orientation.HORIZONTAL);
                //*****Etat
                Label Etat = new Label(u.getEtat());
                Etat.setFont(new Font("Arial", 20));
                Etat.setTextFill(Color.web("#17202A"));
                //**** les info
                Label Email = new Label("Email: " + u.getEmail());
                Email.setTextFill(Color.web("#17202A"));
                Label Ville = new Label("Ville : " + u.getVille());
                Ville.setTextFill(Color.web("#17202A"));
                Label nbrAnn = new Label("Nombre d'année d'expériences : " +u.getNbrAnneeExp());
                nbrAnn.setTextFill(Color.web("#17202A"));
                Label Age = new Label("Age :" + u.getAge());
                Age.setTextFill(Color.web("#17202A"));
                Label numtel = new Label("Numero de telephone :" + u.getNumTe());
                numtel.setTextFill(Color.web("#17202A"));
                Button btn= new Button();
                Button btn2= new Button();
                btn.setText("Envoyer offre");
                btn2.setText("Envoyer Mail");
                
                btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                babysit = u.getId();
                System.out.println(babysit);
                FXMLLoader loader= new FXMLLoader(getClass().getResource("AddOffre.fxml"));
            Parent root;
                try {
                    root = loader.load();
               btn.getScene().setRoot(root);
             } catch (IOException ex) {
                    Logger.getLogger(BabysitterListeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
                btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                babysit = u.getId();
                System.out.println(babysit);
                FXMLLoader loader= new FXMLLoader(getClass().getResource("MailSend.fxml"));
            Parent root;
                try {
                    root = loader.load();
               btn2.getScene().setRoot(root);
             } catch (IOException ex) {
                    Logger.getLogger(BabysitterListeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               
            }
        });

                root.getChildren().addAll(IMG, sep, root2, Etat);
                root2.getChildren().addAll(Username, root3);
                root3.getChildren().addAll(Email, Age, nbrAnn, numtel, btn,btn2);

            } catch (Exception e) {
                System.out.println("exeption du try affiche all event");
            }

            root0.getChildren().add(root);
            root0.setPrefSize(720, 200);
            root0.setPadding(new Insets(0, 0, 0, 0));

        }
        return root0;

    }  
     
      public Node ListofAvis(int babysit) throws SQLException {

          System.out.println(babysit);
        VBox root0 = new VBox(10);
        HBox H0 = new HBox(6);
        VBox V2 = new VBox(10);

        ObservableList<Avis> OA = FXCollections.observableArrayList(CA.findByBabysitter(babysit));

          System.out.println(OA);
        User u = CB.findById(babysit);
          System.out.println(u);
        File file = new File("C:\\wamp\\www\\PI4\\PI4\\web\\images\\" + u.getNom_image());
        System.out.println(u.getNom_image());
        Image image = new Image(file.toURI().toString());
        ImageView IMG = new ImageView(image);
        IMG.fitHeightProperty().set(200);
        IMG.fitWidthProperty().set(200);
        IMG.preserveRatioProperty().set(true);
        Separator sep = new Separator(Orientation.VERTICAL);
        VBox root2 = new VBox(6);
        root2.prefWidthProperty().set(1000);
        root2.prefHeightProperty().set(200);
        root2.setPadding(new Insets(4, 4, 4, 4));
        root2.setStyle("-fx-background-color: #1FB1C8");
        //les information des babysitter
        //*****Username
        Label Username = new Label(u.getUsername());
        Username.setFont(new Font("Arial", 30));
        Username.setTextFill(Color.web("#17202A"));
        //*******description
        Label e = new Label(u.getEtat());
        e.backgroundProperty().set(Background.EMPTY);
        e.setTextFill(Color.web("#17202A"));
        VBox root3 = new VBox(3);
        Separator sep2 = new Separator(Orientation.HORIZONTAL);
        //*****Etat
        Label Etat = new Label(u.getEtat());
        Etat.setFont(new Font("Arial", 20));
        Etat.setTextFill(Color.web("#17202A"));
        //**** les info
        Label Email = new Label("Email: " + u.getEmail());
        Email.setTextFill(Color.web("#17202A"));
        Label Ville = new Label("Ville : " + u.getVille());
        Ville.setTextFill(Color.web("#17202A"));
        Label nbrAnn = new Label("Nombre d'année d'expériences : " + u.getNbrAnneeExp());
        nbrAnn.setTextFill(Color.web("#17202A"));
        Label Age = new Label("Age :" + u.getAge());
        Age.setTextFill(Color.web("#17202A"));
        Label numtel = new Label("Numero de telephone :" + u.getNumTe());
        numtel.setTextFill(Color.web("#17202A"));
        HBox root = new HBox(10);
        root.setAlignment(Pos.CENTER_LEFT);
        root.setStyle("-fx-Border-color:  #2471A3");
        root.setPadding(new Insets(5, 5, 5, 5));
        VBox BigAvis = new VBox(20);
        BigAvis.prefWidthProperty().set(1000);
        BigAvis.prefHeightProperty().set(200);
        BigAvis.setPadding(new Insets(4, 4, 4, 4));
        for (int i = 0; i < OA.size(); i++) {
            BigAvis.prefWidthProperty().set(1000);
            BigAvis.prefHeightProperty().set(200);
            BigAvis.setPadding(new Insets(4, 4, 4, 4));
            HBox rootavis = new HBox(10);

            try {
                VBox rootusername = new VBox(6);
                VBox rootlesbtn = new VBox(6);
                rootusername.setPadding(new Insets(5, 5, 5, 5));

                rootusername.setPadding(new Insets(4, 4, 4, 4));
                Avis a = OA.get(i);
                Label nomParent = new Label(CB.findById(a.getId_parent_id()).getUsername());
                nomParent.setFont(new Font("Arial", 20));
                nomParent.setTextFill(Color.web("#17202A"));
                Label avisP = new Label(a.getMonAvis());
                avisP.setFont(new Font("Arial", 15));
                avisP.setTextFill(Color.web("#17202A"));

                //the 2 buttons delate and update
                if (a.getId_parent_id() == CurrentUser.getId()) {
                    Button btnupd = new Button("modifier");

                    Button btndel = new Button("Supprimer");
                    btndel.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            CA.deleteAvis(a.getId());
                            try{
                            ListeBaby.setContent(ListofAvis(babysit));

                         } catch (SQLException ex) {

                              }
                        }
                    });
                    btnupd.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            Dialog dialog = new Dialog();
                            GridPane grid = new GridPane();
                            TextArea txt = new TextArea();
                            txt.setText(a.getMonAvis());
                            txt.setWrapText(true);
                            grid.getChildren().add(txt);
                            ButtonType Modifier = new ButtonType("Modifier", ButtonBar.ButtonData.OK_DONE);

                            dialog.getDialogPane().setContent(grid);
                            dialog.getDialogPane().getButtonTypes().add(Modifier);

                            dialog.setResultConverter(new Callback() {
                                @Override
                                public Object call(Object param) {
                                    if (param == Modifier) {
                                        try {
                                            a.setMonAvis(txt.getText());
                                            CA.updateAvis(a, a.getId());
                                        } catch (SQLException ex) {
                                            Logger.getLogger(AvisParentController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    return null;
                                }
                            });
                              dialog.showAndWait();
                           
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("AvisParent.fxml"));
                            Parent root;
                            try {
                                ListeBaby.setContent(ListofAvis(babysit));

                           } catch (SQLException ex) {

                                }
                        }
                    });

                    rootlesbtn.getChildren().addAll(btndel,btnupd);
                }

                rootusername.getChildren().add(nomParent);
                rootavis.getChildren().addAll(rootusername, avisP, rootlesbtn);
                BigAvis.getChildren().add(rootavis);

            } catch (Exception ex) {
                System.out.println("exeption du try affiche all event");
            }

        }
        TextArea text = new TextArea();

        Button commenter = new Button("Commenter");
        commenter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String com = text.getText();

                if (com.equals("") || com.equals(null)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur de saisie");
                    alert.setHeaderText("Attention");
                    alert.setContentText("Vous n'avez pas saisie un avis !");
                    alert.showAndWait();
                } else {
                    Avis un_avis = new Avis();
                    un_avis.setId_parent_id(CurrentUser.getId());
                    un_avis.setId_babysitter_id(babysit);
                    un_avis.setMonAvis(com);
                    try {
                        CA.insertAvis(un_avis);
                       
                        
            ListeBaby.setContent(ListofAvis(babysit));

        } catch (SQLException ex) {

        }
                }
            }
        });

        root0.getChildren().addAll(root, BigAvis, text, commenter);
        root0.setPrefSize(720, 200);
        root0.setPadding(new Insets(0, 0, 0, 0));
        root.getChildren().addAll(IMG, sep, root2, Etat);
        root2.getChildren().addAll(Username, root3);
        root3.getChildren().addAll(Etat, Email, Ville, numtel, nbrAnn, Age);
        return root0;

    }

    @FXML
    private void SearchByNom(KeyEvent event) {
    }


    @FXML
    private void FiltrerPArAge(ActionEvent event) {
    }

    @FXML
    private void ChercherParNom(ActionEvent event) {
    }
    
}
