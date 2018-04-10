/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import com.jfoenix.controls.JFXButton;
import edu.AllForKids.entities.Offre;
import static edu.AllForKids.gui.BabysitterListeController.CurrentUser;
import edu.AllForKids.services.CrudOffre;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficheParentOffreController implements Initializable {
    

    @FXML
    private TableColumn<Offre,Integer> id_bbTab;
    @FXML
    private TableColumn<Offre,Integer> nbrenfTab;
    @FXML
    private TableColumn<Offre,String> servTab;
    @FXML
    private TableColumn<Offre,Integer> ageTab;
    @FXML
    private TableColumn<Offre,Date> dateTab;
    @FXML
    private TableColumn<Offre,String> temTab;
    
    @FXML
    private TableColumn<Offre,String> villeTab;
    @FXML
    private TableColumn<Offre,String> etatTab;
    @FXML
    private TableView<Offre> TabPar;



    @FXML
    private AnchorPane Popup;
    @FXML
    private JFXButton Modify;
    @FXML
    private JFXButton Delete;
    
    public static Offre off;
    
    Boolean left = true;
    @FXML
    private ScrollPane ScrollP;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoginController lg =new LoginController();
        CurrentUser = lg.CurrentUser;
        // TODO
        Popup.setVisible(false);
        ScrollP.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        ScrollP.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        VBox vb = new VBox();
        
        
        vb.getChildren().add(TabPar);
        vb.setVgrow(TabPar, Priority.SOMETIMES);
        ScrollP.setContent(vb);
        
        
        
        CrudOffre crudoffre=new CrudOffre();
        List<Offre> myList= (ArrayList) crudoffre.findByParent(16);
        ObservableList<Offre> data=FXCollections.observableArrayList();
        for(Offre o : myList)
        {
         data.add(o);
        }
        
        System.out.println(data);
        TabPar.setItems(data);
        //ObservableList observableList = FXCollections.observableArrayList(arrayList);
        
      id_bbTab.setCellValueFactory(new PropertyValueFactory<>("babysitter_id"));
        nbrenfTab.setCellValueFactory(new PropertyValueFactory<>("NbrEnfant"));
        servTab.setCellValueFactory(new PropertyValueFactory<>("Service"));
       ageTab.setCellValueFactory(new PropertyValueFactory<>("Age"));
       dateTab.setCellValueFactory(new PropertyValueFactory<>("DateOffre"));
        temTab.setCellValueFactory(new PropertyValueFactory<>("temps"));
        villeTab.setCellValueFactory(new PropertyValueFactory<>("ville"));
        etatTab.setCellValueFactory(new PropertyValueFactory<>("Accept"));
        etatTab.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Offre, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Offre, String> param) {
            if(param.getValue().getAccept()==0)
            {
                return new SimpleStringProperty("En attente");
            }
            return new SimpleStringProperty("Accepte");
            }
        });
        
        TabPar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { 
                
          if(left) {
            Popup.setTranslateX(event.getX());
            Popup.setTranslateY(event.getY());
            Popup.setVisible(true);
            left=false;
          } else {
              Popup.setVisible(false);
              left=true;
          }
            }
        });
          
    }    

    @FXML
    private void updateOffre(ActionEvent event) {
        Offre SelectedForEdit = TabPar.getSelectionModel().getSelectedItem();
        off = SelectedForEdit;
        System.out.println(SelectedForEdit);
        try { 
            FXMLLoader loader = new FXMLLoader (getClass().getResource("ModifierOffre.fxml"));
            Parent root = loader.load();
           
//            AddOffreController dpc =loader.getController(); 
//            String nbrEnf = ""+SelectedForEdit.getNbrEnfant()+"";
//            dpc.setNbrEnfantbtn(nbrEnf);
//            dpc.setServicebtn(SelectedForEdit.getService());
//            String age = ""+SelectedForEdit.getAge()+"";
//            dpc.setAgebtn(age);
            
            TabPar.getScene().setRoot(root);
                          
                    } catch (IOException ex) {
            Logger.getLogger(AfficheParentOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void DeleteOffre(ActionEvent event) {
    }
    
}
