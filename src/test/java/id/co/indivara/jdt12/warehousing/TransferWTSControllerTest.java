package id.co.indivara.jdt12.warehousing;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.indivara.jdt12.warehousing.entity.*;
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
public class TransferWTSControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void transferWTSTest() throws Exception {

        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseCode("wrh1");

        Merchandise merchandise = new Merchandise();
        merchandise.setMerchandiseCode("mrc1");

        Store store = new Store();
        store.setStoreCode("str1");

        TransferWTS transferWTS = new TransferWTS();
        transferWTS.setStock(200);

        mockMvc.perform(
                        post("/transferwts/{warehouseCode}/{merchandiseCode}/{storeCode}",warehouse.getWarehouseCode(),merchandise.getMerchandiseCode(),store.getStoreCode())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(merchandise.getMerchandiseCode()))
                                .content(objectMapper.writeValueAsString(warehouse.getWarehouseCode()))
                                .content(objectMapper.writeValueAsString(store.getStoreCode()))
                                .content(objectMapper.writeValueAsString(transferWTS)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transferWTSId").exists())
                .andExpect(jsonPath("$.transferWTSCode").exists())
                .andExpect(jsonPath("$.source.warehouseCode").value("wrh1")) //yang ngirim
                .andExpect(jsonPath("$.merchandiseCode.merchandiseCode").value("mrc1"))
                .andExpect(jsonPath("$.destination.storeCode").value("str1")) //yang nerima
                .andExpect(jsonPath("$.stock").value(200)) //jumlah yang di transfer
                .andExpect(jsonPath("$.timestamp").exists());

        mockMvc.perform(get("/view/warehouseinventory/warehouse/{warehouseCode}", warehouse.getWarehouseCode())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]stock").value(800)); //expect di tempat yg ngirim bakal berkurang jadi berapa

        mockMvc.perform(get("/view/storeinventory/store/{storeCode}", store.getStoreCode())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]stock").value(1450)); //expect di tempat yg nerima bakal nambah jadi berapa

    }
}
