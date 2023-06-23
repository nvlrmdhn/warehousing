package id.co.indivara.jdt12.warehousing;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.jdt12.warehousing.entity.Store;
import id.co.indivara.jdt12.warehousing.repo.StoreRepository;
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
public class StoreControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    StoreRepository storeRepository;

    @Test
    public void storeCreateTest() throws Exception {

        Store store = new Store();
        store.setStoreName("Store Noval");
        store.setStoreLocation("Bekasi");

        mockMvc.perform(
                        post("/create/store")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(store)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeId").exists())
                .andExpect(jsonPath("$.storeCode").exists())
                .andExpect(jsonPath("$.storeName").value("Store Noval"))
                .andExpect(jsonPath("$.storeLocation").value("Bekasi"))
                .andExpect(jsonPath("$.joinDate").exists());
    }

    @Test
    public void storeFindTest() throws Exception {
        mockMvc.perform(
                        get("/find/store/{storeId}","str1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeId").exists())
                .andExpect(jsonPath("$.storeCode").exists())
                .andExpect(jsonPath("$.storeName").value("Store Agil"))
                .andExpect(jsonPath("$.storeLocation").value("Bekasi"))
                .andExpect(jsonPath("$.joinDate").exists());
    }

}
