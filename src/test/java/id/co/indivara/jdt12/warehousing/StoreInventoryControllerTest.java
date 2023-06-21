package id.co.indivara.jdt12.warehousing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreInventoryControllerTest{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAllWarehouseInventoryTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/view/store/all")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(1)))
                .andExpect(jsonPath("$.[0]id").exists())
                .andExpect(jsonPath("$.[0]stock").value(1450))
                .andExpect(jsonPath("$[0].merchandiseCode.merchandiseCode").value("mrc1"))
                .andExpect(jsonPath("$[0].storeCode.storeCode").value("str1 "))
                .andReturn();
    }
}