/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.gui;

import edu.AllForKids.entities.Reservation;
import edu.AllForKids.services.CrudReservation;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ReservationCurrentUserController implements Initializable {

    private TableView<Reservation> ListeReservation;
    private TableColumn<Reservation, Integer> IDReservation;
    private TableColumn<Reservation, Integer> IDEvenement;
    private TableColumn<Reservation, Integer> IDClient;
    private TableColumn<Reservation, Integer> NBTicket;

    /**
     * Initializes the controller class.
     */
    
        private ObservableList<Reservation> data;
LoginController Log;
    @FXML
    private Label UserName;
    @FXML
    private ImageView UserImage;
    @FXML
    private Button BtnAcceuil;
    @FXML
    private Button BtnStore;
    @FXML
    private Button BtnBabySitter;
    @FXML
    private Button BtnEvenent;
    @FXML
    private Button BtnEspace;
    @FXML
    private Button BtnPediatre;
    @FXML
    private ScrollPane SrollPaneMain;
    @FXML
    private AnchorPane ScrollPaneMain;
    @FXML
    private TableView<Reservation> ListeReservation1;
    @FXML
    private TableColumn<Reservation, Integer> IDReservation1;
    @FXML
    private TableColumn<Reservation, Integer> IDEvenement1;
    @FXML
    private TableColumn<Reservation, Integer> IDClient1;
    @FXML
    private TableColumn<Reservation, Integer> DateReserv1;
    @FXML
    private TableColumn<Reservation, Integer> NBTicket1;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            setsCllTable();
            data = FXCollections.observableArrayList();
            loadDataFromDatabase();
            
            CrudReservation rs = new CrudReservation();
            
            List<Reservation> list = rs.displayReserationParUser(Log.CurrentUser.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadDataFromDatabase() throws SQLException {
        CrudReservation rss = new CrudReservation();
        List <Reservation> rvs = rss.displayReserationParUser(Log.CurrentUser.getId());
        for(Reservation rv : rvs )
      {
          data.add(rv);
      }
          
        ListeReservation1.setItems(data);
    }
     
     public void setsCllTable() { 
         
        IDReservation1.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));
        IDEvenement1.setCellValueFactory(new PropertyValueFactory<>("id_even"));
        NBTicket1.setCellValueFactory(new PropertyValueFactory<>("nbre_ticket"));
        IDClient1.setCellValueFactory(new PropertyValueFactory<>("id_client"));
    }


    @FXML
    private void ProfileEdit(MouseEvent event) {
    }

    @FXML
    private void Lougout(MouseEvent event) {
    }

    @FXML
    private void GetAcceuil(ActionEvent event) {
    }

    @FXML
    private void GetStore(ActionEvent event) {
    }

    @FXML
    private void GetBabySitter(ActionEvent event) {
    }

    @FXML
    private void GetEvenement(ActionEvent event) {
    }

    @FXML
    private void GetEspace(ActionEvent event) {
    }

    @FXML
    private void GetPediatre(ActionEvent event) {
    }
    
}
