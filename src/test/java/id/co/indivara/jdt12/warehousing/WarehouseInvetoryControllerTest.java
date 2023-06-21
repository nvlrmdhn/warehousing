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
public class WarehouseInvetoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAllWarehouseInventoryTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/view/warehouse/all")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").value(hasSize(2)))
                .andExpect(jsonPath("$.[0]id").exists())
                .andExpect(jsonPath("$.[0]stock").value(7750))
                .andExpect(jsonPath("$[0].merchandiseCode.merchandiseCode").value("mrc1"))
                .andExpect(jsonPath("$[0].warehouseCode.warehouseCode").value("wrh2"))
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$[1].stock").value(800))
                .andExpect(jsonPath("$[1].merchandiseCode.merchandiseCode").value("mrc1"))
                .andExpect(jsonPath("$[1].warehouseCode.warehouseCode").value("wrh1"))
                .andReturn();
    }
}
