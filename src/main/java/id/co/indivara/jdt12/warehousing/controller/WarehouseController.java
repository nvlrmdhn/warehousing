package id.co.indivara.jdt12.warehousing.controller;

import id.co.indivara.jdt12.warehousing.entity.Warehouse;
import id.co.indivara.jdt12.warehousing.repo.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class WarehouseController {

    @Autowired
    WarehouseRepository warehouseRepository;

    @PostMapping("/create/warehouse")
    public Warehouse createWarehouse(@RequestBody Warehouse warehouse){
        warehouse.setWarehouseCode("wrh"+(warehouseRepository.count()+1));
        warehouse.setWarehouseId(UUID.randomUUID());
        warehouse.setJoinDate(Timestamp.valueOf(LocalDateTime.now()));
        return warehouseRepository.save(warehouse);
    }

    @GetMapping("/find/warehouse/{warehouseId}")
    public Warehouse findById(@PathVariable String warehouseId){
        return warehouseRepository.findById(warehouseId).get();
    }

    @GetMapping("/find/warehouse/all")
    public List<Warehouse> findAll(){
        return warehouseRepository.findAll();
    }


    @DeleteMapping("/delete/warehouse/{warehouseId}")
    public void deleteWarehouse(@PathVariable String warehouseId){
        warehouseRepository.deleteById(warehouseId);
    }
}
