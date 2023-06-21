package id.co.indivara.jdt12.warehousing;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.jdt12.warehousing.entity.Merchandise;
import id.co.indivara.jdt12.warehousing.repo.MerchandiseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MerchandiseControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    MerchandiseRepository merchandiseRepository;

    @Test
    public void merchandiseCreateTest() throws Exception {

        Merchandise merchandise = new Merchandise();
        merchandise.setMerchandiseName("kaos");

        mockMvc.perform(
                        post("/create/merchandise")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(merchandise)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merchandiseId").exists())
                .andExpect(jsonPath("$.merchandiseCode").exists())
                .andExpect(jsonPath("$.merchandiseName").value("kaos"));
    }

    @Test
    public void merchandiseFindTest() throws Exception {
        mockMvc.perform(
                        get("/find/merchandise/{merchandiseId}","mrc1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.merchandiseId").exists())
                .andExpect(jsonPath("$.merchandiseCode").exists())
                .andExpect(jsonPath("$.merchandiseName").value("topi"));
    }

    @Test
    public void deleteMerchandiseTest() throws Exception {
        mockMvc.perform(delete("/delete/merchandise/{merchandiseId}", "mrc3"))
                .andExpect(status().isOk());
        assertFalse(merchandiseRepository.existsById("mrc3"));
    }
}
