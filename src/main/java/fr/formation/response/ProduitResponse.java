package fr.formation.response;

import java.math.BigDecimal;

public class ProduitResponse {

    private int id;
    private String nom;
    private BigDecimal prix;
    
    public String getNom() {
        return nom;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public BigDecimal getPrix() {
        return prix;
    }
    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    


}
