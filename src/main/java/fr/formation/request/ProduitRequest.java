package fr.formation.request;

import java.math.BigDecimal;

public class ProduitRequest {


    private String nom;
    private BigDecimal prix;

    public String getNom() {
        return nom;
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

