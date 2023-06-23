package id.co.indivara.jdt12.warehousing;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.jdt12.warehousing.entity.Merchandise;
import id.co.indivara.jdt12.warehousing.entity.TransferWTW;
import id.co.indivara.jdt12.warehousing.entity.Warehouse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferWTWControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void transferWTWTest() throws Exception {

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseCode("wrh1");

        Warehouse warehouse1 = new Warehouse();
        warehouse1.setWarehouseCode("wrh2");

        Merchandise merchandise = new Merchandise();
        merchandise.setMerchandiseCode("mrc1");

        TransferWTW transferWTW = new TransferWTW();
        transferWTW.setStock(1000);

        mockMvc.perform(
                        post("/transferwtw/{warehouseCodeSource}/{merchandiseCode}/{warehouseCodeDestination}",warehouse.getWarehouseCode(),merchandise.getMerchandiseCode(),warehouse1.getWarehouseCode())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(warehouse.getWarehouseCode()))
                                .content(objectMapper.writeValueAsString(merchandise.getMerchandiseCode()))
                                .content(objectMapper.writeValueAsString(warehouse1.getWarehouseCode()))
                                .content(objectMapper.writeValueAsString(transferWTW)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transferWTWId").exists())
                .andExpect(jsonPath("$.transferWTWCode").exists())
                .andExpect(jsonPath("$.warehouseSource.warehouseCode").value("wrh1"))
                .andExpect(jsonPath("$.merchandiseCode.merchandiseCode").value("mrc1"))
                .andExpect(jsonPath("$.warehouseDestination.warehouseCode").value("wrh2"))
                .andExpect(jsonPath("$.stock").value(1000))
                .andExpect(jsonPath("$.timestamp").exists());

        mockMvc.perform(get("/view/warehouseinventory/warehouse/{warehouseCode}", warehouse.getWarehouseCode())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]stock").value(2000));

        mockMvc.perform(get("/view/warehouseinventory/warehouse/{warehouseCode}", warehouse1.getWarehouseCode())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]stock").value(7750));
    }
}
