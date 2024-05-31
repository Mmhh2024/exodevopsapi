package fr.formation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.ProduitRepository;
import fr.formation.model.Produit;
import fr.formation.request.ProduitRequest;
import fr.formation.response.ProduitResponse;

@RestController
@RequestMapping("/api/produit")
@CrossOrigin("*")
public class ProduitController {

    @Autowired
    private ProduitRepository repository;

    @GetMapping
    public List<ProduitResponse> findAll() {
        List<Produit> produits = this.repository.findAll();
        List<ProduitResponse> response = new ArrayList<>();

        for (Produit produit : produits) {
            ProduitResponse produitResponse = new ProduitResponse();

            BeanUtils.copyProperties(produit, produitResponse);

            response.add(produitResponse);

        }
        
        return response;
    }

    @GetMapping("/{prix1}/{prix2}/between")
    public List<ProduitResponse> findByPrixBetween(@PathVariable float prix1, @PathVariable float prix2) {
       
        List<Produit> produits = this.repository.findByPrixBetween(prix1, prix2);
        List<ProduitResponse> response = new ArrayList<>();

        for (Produit produit : produits) {
            ProduitResponse produitResponse = new ProduitResponse();

            BeanUtils.copyProperties(produit, produitResponse);

            response.add(produitResponse);

        }
        
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody ProduitRequest request) {
        Produit produit = new Produit();
        
        BeanUtils.copyProperties(request, produit);

        this.repository.save(produit);

        return produit.getId();
    }

}
