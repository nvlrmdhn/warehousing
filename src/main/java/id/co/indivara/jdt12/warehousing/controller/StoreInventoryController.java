package id.co.indivara.jdt12.warehousing.controller;

import id.co.indivara.jdt12.warehousing.entity.Merchandise;
import id.co.indivara.jdt12.warehousing.entity.Store;
import id.co.indivara.jdt12.warehousing.entity.StoreInventory;
import id.co.indivara.jdt12.warehousing.repo.StoreInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreInventoryController {

    @Autowired
    StoreInventoryRepository storeInventoryRepository;

    @GetMapping("/view/store/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STOREADMIN')")
    public List<StoreInventory> viewAllStoreInventory(){
        return storeInventoryRepository.findAll();
    }

    @GetMapping("/view/storeinventory/store/{storeCode}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STOREADMIN')")
    public List<StoreInventory> viewStoreInventory(@PathVariable Store storeCode){
        return storeInventoryRepository.findByStoreCode(storeCode);
    }

    @GetMapping("/view/storeinventory/merchandise/{merchandiseCode}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STOREADMIN')")
    public List<StoreInventory> viewStoreInventory(@PathVariable Merchandise merchandiseCode){
        return storeInventoryRepository.findByMerchandiseCode(merchandiseCode);
    }

}
