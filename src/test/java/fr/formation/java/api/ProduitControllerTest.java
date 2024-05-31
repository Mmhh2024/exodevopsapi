package fr.formation.java.api;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.controller.ProduitController;
import fr.formation.dao.ProduitRepository;
import fr.formation.model.Produit;
import fr.formation.request.ProduitRequest;

@WebMvcTest(ProduitController.class)
public class ProduitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduitRepository repository;

    @Test
    void shouldFindAllStatusOk() throws Exception {
        //given

        // when
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/produit")
        )

        // then
        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void shouldAddStatusCreated() throws Exception {
        // given
        ProduitRequest request = new ProduitRequest();
        //request.setNom("produitAjoutTest");
        //request.setPrix(new BigDecimal(1));
        
        Mockito .when(this.repository.save(Mockito.any()))
                .thenReturn(new Produit());

        // when
        this.mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/produit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json(request))
        )

        // then
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldAddProduitAvecNomProduitAjoutTestPrix1() throws Exception {
        // given
        ProduitRequest request = new ProduitRequest();
        request.setNom("produitAjoutTest");
        request.setPrix(new BigDecimal(1));

        Produit produitCree = new Produit();
        produitCree.setId(4);
        produitCree.setNom("produitAjoutTest");
        produitCree.setPrix(new BigDecimal(1));
        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/produit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json(request))
        );

        //then
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id",Matchers.hasValue(4)));
        Assertions.assertEquals(this.repository.findById(4).get(),produitCree);
    }


    private String json(ProduitRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }

}
