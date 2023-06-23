package id.co.indivara.jdt12.warehousing.controller;

import id.co.indivara.jdt12.warehousing.entity.*;
import id.co.indivara.jdt12.warehousing.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class TransferWTWController {
    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;
    @Autowired
    TransferWTWRepository transferWTWRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping("/transferwtw/{warehouseCodeSource}/{merchandiseCode}/{warehouseCodeDestination}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WAREHOUSEADMIN')")
    public ResponseEntity<TransferWTW> transferWTW(@PathVariable Warehouse warehouseCodeSource, @PathVariable Merchandise merchandiseCode, @PathVariable Warehouse warehouseCodeDestination, @RequestBody TransferWTW transferwtw) {
        WarehouseInventory warehouseSource = warehouseInventoryRepository.findByMerchandiseCodeAndWarehouseCode(merchandiseCode,warehouseCodeSource);
        WarehouseInventory warehouseDestination = warehouseInventoryRepository.findByMerchandiseCodeAndWarehouseCode(merchandiseCode,warehouseCodeDestination);

        //ngeset kedalam tabel transferwtw
        transferwtw.setTransferWTWId(UUID.randomUUID());
        transferwtw.setTransferWTWCode("T" + (transferWTWRepository.count()+1));
        transferwtw.setWarehouseSource(warehouseCodeSource);
        transferwtw.setWarehouseDestination(warehouseCodeDestination);
        transferwtw.setMerchandiseCode(merchandiseCode);
        transferwtw.setStock(transferwtw.getStock());
        transferwtw.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transferWTWRepository.save(transferwtw);

        //ngeset kedalam tabel transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setTransactionCode("trx"+(transactionRepository.count()+1));
        transaction.setType("trx_wtw");
        transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transactionRepository.save(transaction);

        //object baru buat warehouse inventory kalo ternyata gak punya merchandise
        WarehouseInventory warehouseInventory = new WarehouseInventory();

        if (warehouseSource.getStock()>= transferwtw.getStock()){
            warehouseSource.setStock(warehouseSource.getStock()-transferwtw.getStock());
        }
        try {
            warehouseDestination.setStock(warehouseDestination.getStock()+ transferwtw.getStock());
            warehouseInventoryRepository.save(warehouseDestination);
        }catch (Exception ex){
            warehouseInventory = new WarehouseInventory();
            warehouseInventory.setWarehouseCode(warehouseCodeDestination);
            warehouseInventory.setMerchandiseCode(merchandiseCode);
            warehouseInventory.setStock(transferwtw.getStock());
            warehouseInventoryRepository.save(warehouseInventory);
        }
        return new ResponseEntity<>(transferwtw,HttpStatus.OK);
    }

    @GetMapping("/find/wtw/all")
    public List<TransferWTW> findAllTransferWTW(){
        return transferWTWRepository.findAll();
    }

}
