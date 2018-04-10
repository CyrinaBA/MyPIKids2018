/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Evenement;
import edu.AllForKids.entities.User;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class CrudBabysitter {
    
    Connection con;
     

    
    public CrudBabysitter() {
    con=MyConnexion.getInstance().getConnection();
    }
    
  

   
    public void insertbabysitter(User user) throws SQLException {
        
 
        String req= "INSERT INTO `User`(Age, Sexe, adresse, nom_image, Ville, nbrAnneeExp, Etat,numTel) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1, user.getAge());
        ste.setString(2, user.getSexe());
        ste.setString(3, user.getAdresse());
        ste.setString(4, user.getNom_image());
        ste.setString(5, user.getVille());
        ste.setInt(6, user.getNbrAnneeExp());
        ste.setString(7, user.getEtat());
        ste.setInt(8, user.getNumTe());
        
        System.out.println(ste);
        ste.executeUpdate();
        
        
               
        
            
    }

 
    public ObservableList<User> displayBabysitter() {
        ObservableList<User> users = FXCollections.observableArrayList();
       

        try {
            String req = "";
             
             req = "SELECT * FROM User WHERE roles='a:1:{i:0;s:14:\"ROLE_BABYSITER\";}'";

              PreparedStatement ste = con.prepareStatement(req);
                ResultSet rs = ste.executeQuery();
                while (rs.next())
                {     User u=new User(
                        
                        rs.getInt("id"),
                        rs.getString("username"),                      
                        rs.getString("email"),
                        rs.getInt("Age"),
                        rs.getString("Sexe"),
                        rs.getString("adresse"),
                        rs.getString("nom_image"),
                        rs.getString("Ville"),
                        rs.getInt("nbrAnneeExp") ,
                        rs.getString("Etat") ,
                        rs.getInt("numTel")
                        
                );
                    
                            
                   users.add(u);
                }
            
        } catch (SQLException ex) {
            System.err.println("EROOR");
        }
        return users;
    }

    
    public User findById(int id) {
        String req="SELECT * FROM User WHERE id='"+id+"'" ;
        ResultSet rs;
        User u=null;
		try {
			rs = con.createStatement().executeQuery(req);
		
       
        while(rs.next()){
           u=new User();
            
                  u.setId(rs.getInt("id"));
                  u.setUsername(rs.getString("username"));
                  u.setEmail(rs.getString("email"));
                  u.setAge(rs.getInt("Age"));
                  u.setSexe(rs.getString("Sexe"));
                  u.setAdresse(rs.getString("adresse"));
                  u.setNom_image(rs.getString("nom_image"));
                  u.setVille(rs.getString("Ville"));
                  u.setNbrAnneeExp(rs.getInt("nbrAnneeExp"));
                  u.setEtat(rs.getString("Etat"));
                  u.setNumTe(rs.getInt("numTel"));

        }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return u;
    }

   
    public void updateBabysitter(User user, int id) throws SQLException {
        
        String req= "Update User Set username=? , email=? , Age=? , Sexe=? , adresse=? ,"
                + " nom_image=? , Ville=?, nbrAnneeExp=?, Etat=? , numTel=?  WHERE id='"+id+"'";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setString(1, user.getUsername());
        ste.setString(2, user.getEmail());
        ste.setInt(3, user.getAge());
        ste.setString(4, user.getSexe());
        ste.setString(5, user.getAdresse());
        ste.setString(6, user.getNom_image());
        ste.setString(7, user.getVille());
        ste.setInt(8, user.getNbrAnneeExp());
        ste.setString(9, user.getEtat());
        ste.setInt(10, user.getNumTe());
        ste.executeUpdate();
    }
    
}
