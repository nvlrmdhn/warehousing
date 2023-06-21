package id.co.indivara.jdt12.warehousing.controller;

import id.co.indivara.jdt12.warehousing.entity.Store;
import id.co.indivara.jdt12.warehousing.repo.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
public class StoreController {

    @Autowired
    StoreRepository storeRepository;

    @PostMapping("/create/store")
    public Store createStore(@RequestBody Store store){
        Store str = new Store();
        str.setStoreCode("str"+(storeRepository.count()+1));
        str.setStoreId(UUID.randomUUID());
        str.setStoreName(store.getStoreName());
        str.setStoreLocation(store.getStoreLocation());
        str.setJoinDate(Timestamp.valueOf(LocalDateTime.now()));
        return storeRepository.save(str);
    }

    @GetMapping("/find/store/{storeId}")
    public Store findById(@PathVariable String storeId){
        return storeRepository.findById(storeId).get();
    }

    @GetMapping("/find/store/all")
    public List<Store> findAll(){
        return storeRepository.findAll();
    }

    @DeleteMapping("/delete/store/{storeId}")
    public void deleteStore(@PathVariable String storeId){
        storeRepository.deleteById(storeId);
    }
}
