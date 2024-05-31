package fr.formation.java.api;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.dao.ProduitRepository;
import fr.formation.model.Produit;
import fr.formation.request.ProduitRequest;

@AutoConfigureMockMvc
public class ProduitControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduitRepository repository;

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
