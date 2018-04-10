/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.User;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class CrudUser {

    Connection cnx = MyConnexion.getInstance().getConnection();
    Statement ste;
    PreparedStatement pst;
    ResultSet rs;

    public void ajouter_utilisateur(User u) {

        try {
            System.out.println(u);

            String query = "INSERT INTO user (username, email,password, roles,  Age,  Sexe, adresse,nom_image) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getEmail());
            pst.setString(3, u.getPassword());
            pst.setString(4, u.getRoles());
            pst.setInt(5, u.getAge());

            pst.setString(6, u.getSexe());

            pst.setString(7, u.getAdresse());
                        pst.setString(8, u.getNom_image());

            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }}
        
         

    public User FindByEmail(String email) {
        try {
            String requete = "SELECT * FROM user where email='" + email + "'";
            ste = cnx.createStatement();
            rs = ste.executeQuery(requete);
            
            while (rs.next()) {
                
                User m = new User(rs.getInt("id"), 
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("sexe"),
                        rs.getString("roles"),
                        rs.getString("password"));
                return m;
            }
            
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }
    
    public User findById(int id) throws SQLException {
        String req="SELECT * FROM User WHERE id='"+id+"'" ;
        ResultSet rs;
        User u=null;
		
			rs = cnx.createStatement().executeQuery(req);
		
       
        while(rs.next()){
           u=new User();
            
                  u.setId(rs.getInt("id"));
                  u.setUsername(rs.getString("username"));
                  u.setEmail(rs.getString("email"));
                  u.setPassword(rs.getString("password"));
                  u.setRoles(rs.getString("roles"));
                  u.setSexe(rs.getString("Sexe"));
                  u.setNom_image(rs.getString("nom_image"));
                  

        }
        return u;
    }
    
     public void updateUser(User user, int id) throws SQLException {
        
        String req= "Update User Set username=? , email=? ,Sexe=? ,"
                + " nom_image=?, password=? WHERE id='"+id+"'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.setString(1, user.getUsername());
        ste.setString(2, user.getEmail());
        ste.setString(3, user.getSexe());
        ste.setString(4, user.getNom_image());
        ste.setString(5,user.getPassword());
        ste.executeUpdate();
    }
     
     public void updateUserBB(User user, int id) throws SQLException {
        
        String req= "Update User Set Age=?, adresse=? ,"
                + " Ville=?, nbrAnneeExp=?, Etat=? , numTel=? WHERE id='"+id+"'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.setInt(1, user.getAge());
        ste.setString(2, user.getAdresse());
        ste.setString(3, user.getVille());
        ste.setInt(4, user.getNbrAnneeExp());
        ste.setString(5, user.getEtat());
        ste.setInt(6, user.getNumTe());
        ste.executeUpdate();
    }
     
     public void updateUserPed(User user, int id) throws SQLException {
        
        String req= "Update User Set Age=?, adresse=? ,"
                + " Ville=?, nbrAnneeExp=?, Etat=? , numTel=? WHERE id='"+id+"'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ste.setInt(1, user.getAge());
        ste.setString(2, user.getAdresse());
        ste.setString(3, user.getVille());
        ste.setInt(6, user.getNumTe());
        ste.executeUpdate();
    }
    


    public  User Authentification(String email , String password) throws SQLException{
        String requete=" SELECT * FROM user where email='"+email+"' AND password='"+password+"'" ;
        ste=cnx.createStatement() ;
        rs=ste.executeQuery(requete);
      
        while(rs.next()){
        
   User m = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),rs.getInt("enabled"),
           rs.getString("password"), rs.getString("roles"),rs.getString("nom_image"));

                
        return m ;
        }
        return null ;
    
    }
    
    
}
