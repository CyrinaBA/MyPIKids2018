/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Offre;
import edu.AllForKids.entities.User;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class CrudOffre {

    Connection con;

    public CrudOffre() {
        con = MyConnexion.getInstance().getConnection();
    }
    
    

    public void insertOffre(Offre offre) throws SQLException {

        String req = "INSERT INTO `Offre`(parent_id, babysitter_id, NbrEnfant, Service, Age, DateOffre, temps,Accept,ville) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1, offre.getParent_id());
        ste.setInt(2, offre.getBabysitter_id());
        ste.setInt(3, offre.getNbrEnfant());
        ste.setString(4, offre.getService());
        ste.setInt(5, offre.getAge());
        ste.setDate(6, (java.sql.Date) offre.getDateOffre());
        ste.setString(7, offre.getTemps());
        ste.setInt(8, offre.getAccept());
        ste.setString(9, offre.getVille());
        System.out.println(ste);
        ste.executeUpdate();

    }

    public void deleteOffre(int id, int parent) {
        try {
            String req = "DELETE FROM `Offre` WHERE id=? and parent_id=? ";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, id);
            ste.setInt(2, parent);
            ste.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error !! ");
        }

    }

    public List<Offre> displayOffre(int parent, int babysitter, User e) {
        List<Offre> offres = new ArrayList<Offre>();

        try {
            String req = "";
            if (e.getId()== parent) {
                req = "SELECT * FROM Offre WHERE parent_id='" + parent + "'";
            } else if (e.getId()== babysitter) {
                req = "SELECT * FROM Offre WHERE babysitter_id='" + babysitter + "'";
            }

            PreparedStatement ste = con.prepareStatement(req);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                Offre o = new Offre(
                        rs.getInt("id"),
                        rs.getInt("parent_id"),
                        rs.getInt("babysitter_id"),
                        rs.getInt("NbrEnfant"),
                        rs.getString("Service"),
                        rs.getInt("Age"),
                        rs.getDate("DateOffre"),
                        rs.getString("temps"),
                        rs.getInt("Accept"),
                        rs.getString("ville"));

                offres.add(o);
            }

        } catch (SQLException ex) {
            System.err.println("EROOR");
        }
        return offres;
    }

    public Offre findById(int id) {
        String req = "SELECT * FROM Offre WHERE id='" + id + "'";
        ResultSet rs;
        Offre o = null;
        try {
            rs = con.createStatement().executeQuery(req);

            while (rs.next()) {
                o = new Offre();

                o.setId(rs.getInt("id"));
                o.setParent_id(rs.getInt("parent_id"));
                o.setBabysitter_id(rs.getInt("babysitter_id"));
                o.setNbrEnfant(rs.getInt("NbrEnfant"));
                o.setService(rs.getString("Service"));
                o.setAge(rs.getInt("Age"));
                o.setDateOffre(rs.getDate("DateOffre"));
                o.setTemps(rs.getString("temps"));
                o.setAccept(rs.getInt("Accept"));
                o.setVille(rs.getString("ville"));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return o;
    }

    public List<Offre> findByParent(int parent) {
        List<Offre> listeOffre = new ArrayList();
        String req = "SELECT * FROM Offre WHERE parent_id=" + parent;
        ResultSet rs;

        try {
            rs = con.createStatement().executeQuery(req);
            

            while (rs.next()) {
                Offre o = new Offre();
                o.setId(rs.getInt("id"));
                o.setParent_id(rs.getInt("parent_id"));
                o.setBabysitter_id(rs.getInt("babysitter_id"));
                o.setNbrEnfant(rs.getInt("NbrEnfant"));
                o.setService(rs.getString("Service"));
                o.setAge(rs.getInt("Age"));
                o.setDateOffre(rs.getDate("DateOffre"));
                o.setTemps(rs.getString("temps"));
                o.setAccept(rs.getInt("Accept"));
                o.setVille(rs.getString("ville"));
                listeOffre.add(o);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listeOffre;

    }

    public void updateOffre(Offre offre, int id) throws SQLException {

        String req = "Update Offre Set NbrEnfant=? , Service=? , Age=? , DateOffre=? , temps=? ,"
                + " Accept=? , ville=? WHERE id='" + id + "'";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1, offre.getNbrEnfant());
        ste.setString(2, offre.getService());
        ste.setInt(3, offre.getAge());
        ste.setDate(4, (java.sql.Date) offre.getDateOffre());
        ste.setString(5, offre.getTemps());
        ste.setInt(6, offre.getAccept());
        ste.setString(7, offre.getVille());
        ste.executeUpdate();
    }

}
