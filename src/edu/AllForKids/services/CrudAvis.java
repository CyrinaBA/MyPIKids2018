/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Avis;
import edu.AllForKids.entities.Offre;
import edu.AllForKids.entities.User;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class CrudAvis {
    
    Connection con ;
     public CrudAvis() {
    con=MyConnexion.getInstance().getConnection();
    }
    
   
    public void insertAvis(Avis avis) throws SQLException {
 
        String req= "INSERT INTO `Avis`(`id`, `id_parent_id`, `id_babysitter_id`, `monAvis`) VALUES (?,?,?,?)";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1, avis.getId());
        ste.setInt(2, avis.getId_parent_id());
        ste.setInt(3, avis.getId_babysitter_id());
        ste.setString(4, avis.getMonAvis());
        ste.executeUpdate();         
        
    }

   
    public void deleteAvis(int id) {
        try {
            String req= "DELETE FROM `Avis` WHERE id=?";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Error !! ");
        }

        
               
        
    }

 
    public List<Avis> displayAvis(int parent, int babysitter,User e) {
        List<Avis> lesavis=new ArrayList<Avis>();
       

        try {
            String req = "";
            if (e.getId()== parent) {
             req = "SELECT * FROM Offre WHERE id_parent_id='"+parent+"'";
             } else if (e.getId()== babysitter) {
                req = "SELECT * FROM Avis WHERE id_babysitter_id='"+babysitter+"'";
             }
             
            
           
              PreparedStatement ste = con.prepareStatement(req);
                ResultSet rs = ste.executeQuery();
                while (rs.next())
                {     Avis o=new Avis(
                        rs.getInt("id"),
                        rs.getInt("id_parent_id"),
                        rs.getInt("id_babysitter_id"),
                      
                        rs.getString("monAvis")
                       );
                    
                            
                   lesavis.add(o);
                }
            
        } catch (SQLException ex) {
            System.err.println("EROOR");
        }
        return lesavis;
    }

    
    public Avis findById(int id) {
        String req="SELECT * FROM Avis WHERE id='"+id+"'" ;
        ResultSet rs;
        Avis a=null;
		try {
			rs = con.createStatement().executeQuery(req);
		
       
        while(rs.next()){
           a=new Avis();
            
                  a.setId(rs.getInt("id"));
                  a.setId_parent_id(rs.getInt("id_parent_id"));
                  a.setId_babysitter_id(rs.getInt("id_babysitter_id"));
                 
                  a.setMonAvis(rs.getString("monAvis"));
                 

        }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return a;
    }

    
    public List<Avis> findByParent(int parent) {
        List<Avis> listeAvis = new ArrayList();
          String req="SELECT * FROM Avis WHERE id_parent_id='"+parent+"'" ;
        ResultSet rs;
        
		try {
			rs = con.createStatement().executeQuery(req);
		
       
        while(rs.next()){
          Avis a = new Avis();
          a.setId_parent_id(rs.getInt("id_parent_id"));
          a.setId_babysitter_id(rs.getInt("id_babysitter_id"));
          a.setMonAvis(rs.getString("monAvis"));
          listeAvis.add(a);

        }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return listeAvis;
       

    }
    public List<Avis> findByBabysitter(int babysitter) {
        List<Avis> listeAvis = new ArrayList();
        String req="SELECT * FROM Avis WHERE id_babysitter_id='"+babysitter+"'" ;
        ResultSet rs;
        
		try {
			rs = con.createStatement().executeQuery(req);
		
       
        while(rs.next()){
          Avis a = new Avis();
          a.setId(rs.getInt("id"));
          a.setId_parent_id(rs.getInt("id_parent_id"));
          a.setId_babysitter_id(rs.getInt("id_babysitter_id"));
          a.setMonAvis(rs.getString("monAvis"));
          listeAvis.add(a);

        }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return listeAvis;
       

    }

   
    public void updateAvis(Avis avis, int id) throws SQLException {
        
        String req= "Update `Avis` Set monAvis=? WHERE id='"+id+"'";
        PreparedStatement ste = con.prepareStatement(req);
        
        ste.setString(1, avis.getMonAvis());
       
        ste.executeUpdate();
    }
}
