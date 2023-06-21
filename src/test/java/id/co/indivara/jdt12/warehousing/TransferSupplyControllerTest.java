package id.co.indivara.jdt12.warehousing;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.jdt12.warehousing.entity.Merchandise;
import id.co.indivara.jdt12.warehousing.entity.TransferSupply;
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
public class TransferSupplyControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void transferSupplyTest() throws Exception {

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseCode("wrh1");

        Merchandise merchandise = new Merchandise();
        merchandise.setMerchandiseCode("mrc1");

        TransferSupply transferSupply = new TransferSupply();
        transferSupply.setStock(1000);

        mockMvc.perform(
                        post("/transfer/{warehouseCode}/{merchandiseCode}",warehouse.getWarehouseCode(),merchandise.getMerchandiseCode())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(warehouse.getWarehouseCode()))
                                .content(objectMapper.writeValueAsString(merchandise.getMerchandiseCode()))
                                .content(objectMapper.writeValueAsString(transferSupply)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.trxSupplyId").exists())
                .andExpect(jsonPath("$.trxSupplyCode").exists())
                .andExpect(jsonPath("$.warehouse.warehouseCode").value("wrh1"))
                .andExpect(jsonPath("$.merchandise.merchandiseCode").value("mrc1"))
                .andExpect(jsonPath("$.stock").value(1000))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}
