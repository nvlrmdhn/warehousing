package id.co.indivara.jdt12.warehousing.controller;

import id.co.indivara.jdt12.warehousing.entity.Merchandise;
import id.co.indivara.jdt12.warehousing.entity.Warehouse;
import id.co.indivara.jdt12.warehousing.entity.WarehouseInventory;
import id.co.indivara.jdt12.warehousing.repo.WarehouseInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WarehouseInventoryController {

    @Autowired
    WarehouseInventoryRepository warehouseInventoryRepository;

    @GetMapping("/view/warehouse/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WAREHOUSEADMIN')")
    public List<WarehouseInventory> viewAllWarehouseInventory(){
        return warehouseInventoryRepository.findAll();
    }

    @GetMapping("/view/warehouseinventory/warehouse/{warehouseCode}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WAREHOUSEADMIN')")
    public List<WarehouseInventory> viewWarehouseInventory(@PathVariable Warehouse warehouseCode){
       return warehouseInventoryRepository.findByWarehouseCode(warehouseCode);
    }

    @GetMapping("/view/warehouseinventory/merchandise/{merchandiseCode}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('WAREHOUSEADMIN')")
    public List<WarehouseInventory> viewWarehouseInventory(@PathVariable Merchandise merchandiseCode){
        return warehouseInventoryRepository.findByMerchandiseCode(merchandiseCode);
    }

}
