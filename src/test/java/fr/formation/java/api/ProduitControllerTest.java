package fr.formation.java.api;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.dao.ProduitRepository;
import fr.formation.model.Produit;
import fr.formation.request.ProduitRequest;

public class ProduitControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
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

    private String json(ProduitRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }
}
