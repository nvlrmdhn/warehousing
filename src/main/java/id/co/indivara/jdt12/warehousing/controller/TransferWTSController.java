package id.co.indivara.jdt12.warehousing.controller;

import id.co.indivara.jdt12.warehousing.entity.*;
import id.co.indivara.jdt12.warehousing.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class TransferWTSController{
    @Autowired
    StoreInventoryRepository storeInventoryRepository;
    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;
    @Autowired
    TransferWTSRepository transferWTSRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/transferwts/{warehouseCode}/{merchandiseCode}/{storeCode}")
    public ResponseEntity<TransferWTS> transferWTS(@PathVariable Warehouse warehouseCode, @PathVariable Merchandise merchandiseCode, @PathVariable Store storeCode, @RequestBody TransferWTS transferWTS){
        WarehouseInventory warehouseSource = warehouseInventoryRepository.findByMerchandiseCodeAndWarehouseCode(merchandiseCode,warehouseCode);
        StoreInventory storeDestination = storeInventoryRepository.findByMerchandiseCodeAndStoreCode(merchandiseCode,storeCode);

        //object baru buat warehouse inventory kalo ternyata gak punya merchandise
        StoreInventory storeInventory = new StoreInventory();

        //ngeset kedalam tabel transferwts
        transferWTS.setTransferWTSId(UUID.randomUUID());
        transferWTS.setTransferWTSCode("T" + (transferWTSRepository.count()+1));
        transferWTS.setMerchandiseCode(merchandiseCode);
        transferWTS.setSource(warehouseCode);
        transferWTS.setDestination(storeCode);
        transferWTS.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transferWTSRepository.save(transferWTS);

        //ngeset kedalam tabel transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setTransactionCode("trx"+(transactionRepository.count()+1));
        transaction.setType("trx_wts");
        transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transactionRepository.save(transaction);

        if (warehouseSource.getStock()>=transferWTS.getStock()){
            warehouseSource.setStock(warehouseSource.getStock()- transferWTS.getStock());
        }
        try {
            storeDestination.setStock(storeDestination.getStock()+transferWTS.getStock());
            storeInventoryRepository.save(storeDestination);
        }catch (Exception ex){
            storeInventory.setStoreCode(storeCode);
            storeInventory.setMerchandiseCode(merchandiseCode);
            storeInventory.setStock(transferWTS.getStock());
            storeInventoryRepository.save(storeInventory);
        }
        return new ResponseEntity<>(transferWTS, HttpStatus.OK);
    }

    @GetMapping("/find/wts/all")
    public List<TransferWTS> findAllTransferWTS(){
        return transferWTSRepository.findAll();
    }

}
