/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.entities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Offre {
    
    private int id;
    private int parent_id;
    private int babysitter_id;
    private int NbrEnfant;
    private String Service;
    private int Age;
    private Date DateOffre;
    private String temps;
    private int Accept;
    private String ville;
    
    
    
    public Offre(){}

    public Offre(int id, int parent_id, int babysitter_id, int NbrEnfant, String Service, int Age, Date DateOffre, String temps, int Accept, String ville) {
        this.id = id;
        this.parent_id = parent_id;
        this.babysitter_id = babysitter_id;
        this.NbrEnfant = NbrEnfant;
        this.Service = Service;
        this.Age = Age;
        this.DateOffre = DateOffre;
        this.temps = temps;
        this.Accept = Accept;
        this.ville = ville;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getBabysitter_id() {
        return babysitter_id;
    }

    public void setBabysitter_id(int babysitter_id) {
        this.babysitter_id = babysitter_id;
    }

    public int getNbrEnfant() {
        return NbrEnfant;
    }

    public void setNbrEnfant(int NbrEnfant) {
        this.NbrEnfant = NbrEnfant;
    }

    public String getService() {
        return Service;
    }

    public void setService(String Service) {
        this.Service = Service;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public Date getDateOffre() {
        return DateOffre;
    }

    public void setDateOffre(Date DateOffre) {
        this.DateOffre = DateOffre;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public int getAccept() {
        return Accept;
    }

    public void setAccept(int Accept) {
        this.Accept = Accept;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.parent_id;
        hash = 89 * hash + this.babysitter_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offre other = (Offre) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.parent_id != other.parent_id) {
            return false;
        }
        if (this.babysitter_id != other.babysitter_id) {
            return false;
        }
        return true;
    }

    public void setDateOffre(LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", parent_id=" + parent_id + ", babysitter_id=" + babysitter_id + ", NbrEnfant=" + NbrEnfant + ", Service=" + Service + ", Age=" + Age + ", DateOffre=" + DateOffre + ", temps=" + temps + ", Accept=" + Accept + ", ville=" + ville + '}';
    }
    
    

    
}
