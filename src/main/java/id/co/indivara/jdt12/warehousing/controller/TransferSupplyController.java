package id.co.indivara.jdt12.warehousing.controller;

import id.co.indivara.jdt12.warehousing.entity.*;
import id.co.indivara.jdt12.warehousing.repo.TransactionRepository;
import id.co.indivara.jdt12.warehousing.repo.TransferSupplyRepository;
import id.co.indivara.jdt12.warehousing.repo.WarehouseInventoryRepository;
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
public class TransferSupplyController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransferSupplyRepository transferSupplyRepository;
    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;

    @PostMapping("/transfer/{warehouseCode}/{merchandiseCode}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPPLYER')")
    public ResponseEntity<TransferSupply> transferSupply(@PathVariable Warehouse warehouseCode, @PathVariable Merchandise merchandiseCode, @RequestBody TransferSupply transferSupply){
        //buat cek warehousenya ada dan punya merchandise
        WarehouseInventory warehouseInventory = warehouseInventoryRepository.findByMerchandiseCodeAndWarehouseCode(merchandiseCode,warehouseCode);

        //ngeset kedalam tabel transfersupply
        transferSupply.setTrxSupplyCode("T" + (transferSupplyRepository.count()+1));
        transferSupply.setTrxSupplyId(UUID.randomUUID());
        transferSupply.setMerchandise(merchandiseCode);
        transferSupply.setWarehouse(warehouseCode);
        transferSupply.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));

        //ngeset kedalam tabel transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionCode("trx"+(transactionRepository.count()+1));
        transaction.setTransactionId(UUID.randomUUID());
        transaction.setType("trx_supply");
        transaction.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        transactionRepository.save(transaction);

        //object baru buat warehouse inventory kalo ternyata gak punya merchandise
        WarehouseInventory warehouseInventory1 = new WarehouseInventory();

        try {
            warehouseInventory.setStock(warehouseInventory.getStock()+transferSupply.getStock());
            warehouseInventoryRepository.save(warehouseInventory);
        }catch (Exception ex){
            warehouseInventory1.setMerchandiseCode(merchandiseCode);
            warehouseInventory1.setWarehouseCode(warehouseCode);
            warehouseInventory1.setStock(transferSupply.getStock());
            warehouseInventoryRepository.save(warehouseInventory1);
        }
        transferSupplyRepository.save(transferSupply);
        return new ResponseEntity<>(transferSupply,HttpStatus.OK);
    }

    @GetMapping("/find/supply/all")
    public List<TransferSupply> findAllTransferSupply(){
        return transferSupplyRepository.findAll();
    }
}
