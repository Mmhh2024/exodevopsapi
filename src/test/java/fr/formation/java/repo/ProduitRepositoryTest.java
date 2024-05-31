package fr.formation.java.repo;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProduitRepositoryTest {

    @Autowired
    ProduitRepository repoProduit;

    @Test
    void testFindAll() {
        // given

        // when
        List<Produit> produits = this.repoProduit.findAll();

        // then
        Assertions.assertNotNull(produits);
        Assertions.assertTrue(produits.size() > 0);
        Assertions.assertEquals(3, produits.size());
        Assertions.assertEquals("a2", produits.get(1).getId());
    }

    @Test
    void shouldAdd() {
        //given
        Produit produit = new Produit();
        String randomName = UUID.randomUUID().toString();

        produit.setNom(randomName);
        
        Assertions.assertEquals(null, produit.getId());

        // when
        this.repoProduit.save(produit);

        // then
        Assertions.assertNotEquals(null, produit.getId());
        Assertions.assertEquals(randomName, this.repoProduit.findById(produit.getId()).get().getNom());
    }


}
