package fr.formation.api;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.request.ProduitRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class ProduitControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(statements = "DELETE FROM produit WHERE id=4;",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
    void shouldAddProduitAvecNomProduitAjoutTestPrix1() throws Exception {
        // given
        ProduitRequest request = new ProduitRequest();
        request.setNom("produitAjoutTest");
        request.setPrix(new BigDecimal(1));

        // when
        ResultActions result = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/produit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.json(request)));

        // then
        result.andExpect(MockMvcResultMatchers.content().string("4"));
    }

    @Test
    void shouldFind3Products() throws Exception{
        //given testDB

        //when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/produit"));

        //then
        result.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
        .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$[1].nom").value("fraises"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[2].prix").value(0.8));
    }

    private String json(ProduitRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }
}
