package id.co.indivara.jdt12.warehousing;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.jdt12.warehousing.entity.Warehouse;
import id.co.indivara.jdt12.warehousing.repo.WarehouseRepository;
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
public class WarehouseControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WarehouseRepository warehouseRepository;

    @Test
    public void warehouseCreateTest() throws Exception {

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseName("Warehouse Keren");
        warehouse.setWarehouseLocation("Depok");

        mockMvc.perform(
                        post("/create/warehouse")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(warehouse)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.warehouseId").exists())
                .andExpect(jsonPath("$.warehouseCode").exists())
                .andExpect(jsonPath("$.warehouseName").value("Warehouse Keren"))
                .andExpect(jsonPath("$.warehouseLocation").value("Depok"))
                .andExpect(jsonPath("$.joinDate").exists());
    }

    @Test
    public void warehouseFindTest() throws Exception {
        mockMvc.perform(
                        get("/find/warehouse/{warehouseId}","wrh1")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.warehouseId").exists())
                .andExpect(jsonPath("$.warehouseCode").exists())
                .andExpect(jsonPath("$.warehouseName").value("Warehouse Noval"))
                .andExpect(jsonPath("$.warehouseLocation").value("Jakarta"))
                .andExpect(jsonPath("$.joinDate").exists());
    }
}
