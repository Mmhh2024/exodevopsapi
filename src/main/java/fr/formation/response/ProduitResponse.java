package fr.formation.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProduitResponse {

    private int id;
    private String nom;
    private BigDecimal prix;

}
