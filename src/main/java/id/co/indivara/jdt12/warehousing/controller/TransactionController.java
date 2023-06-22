package id.co.indivara.jdt12.warehousing.controller;

import id.co.indivara.jdt12.warehousing.entity.TransferSupply;
import id.co.indivara.jdt12.warehousing.entity.TransferWTS;
import id.co.indivara.jdt12.warehousing.entity.TransferWTW;
import id.co.indivara.jdt12.warehousing.entity.Warehouse;
import id.co.indivara.jdt12.warehousing.repo.TransactionRepository;
import id.co.indivara.jdt12.warehousing.repo.TransferSupplyRepository;
import id.co.indivara.jdt12.warehousing.repo.TransferWTSRepository;
import id.co.indivara.jdt12.warehousing.repo.TransferWTWRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransferSupplyRepository transferSupplyRepository;
    @Autowired
    TransferWTSRepository transferWTSRepository;
    @Autowired
    TransferWTWRepository transferWTWRepository;

    public List<TransferSupply> transferSupply(Warehouse warehouse){
        return transferSupplyRepository.findByWarehouse(warehouse);
    }

    public List<TransferWTW> transferWTW(Warehouse warehouse){
        return transferWTWRepository.findByWarehouseSource(warehouse);
    }

    public List<TransferWTS> transferWTS(Warehouse warehouse){
        return transferWTSRepository.findBySource(warehouse);
    }

    @GetMapping("/find/transaction/{warehouseCode}")
    public Map<List,Object> findTransaction(@PathVariable("warehouseCode") Warehouse warehouse){
        Map combine = new HashMap<List,Object>();
        combine.put("trx_supply",transferSupply(warehouse));
        combine.put("trx_wtw",transferWTW(warehouse));
        combine.put("trx_wts",transferWTS(warehouse));
        return combine;
    }

    @GetMapping("/find/transaction/supply/{warehouseCode}")
    public List<TransferSupply> findTransactionSupply(@PathVariable Warehouse warehouseCode){
        return transferSupplyRepository.findByWarehouse(warehouseCode);
    }

    @GetMapping("/find/transaction/wtw/{warehouseCode}")
    public List<TransferWTW> findTransactionWTW(@PathVariable Warehouse warehouseCode){
        return transferWTWRepository.findByWarehouseSource(warehouseCode);
    }

    @GetMapping("/find/transaction/wts/{warehouseCode}")
    public List<TransferWTS> findTransactionWTS(@PathVariable Warehouse warehouseCode){
        return transferWTSRepository.findBySource(warehouseCode);
    }
}
