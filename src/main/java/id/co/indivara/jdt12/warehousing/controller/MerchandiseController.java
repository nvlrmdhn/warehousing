package id.co.indivara.jdt12.warehousing.controller;

import id.co.indivara.jdt12.warehousing.entity.Merchandise;
import id.co.indivara.jdt12.warehousing.repo.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class MerchandiseController {

    @Autowired
    MerchandiseRepository merchandiseRepository;

    @PostMapping("/create/merchandise")
    @PreAuthorize("hasRole('ADMIN')")
    public Merchandise createMerchandise(@RequestBody Merchandise merchandise) {
        Merchandise ms = new Merchandise();
        ms.setMerchandiseCode("mrc" + (merchandiseRepository.count() + 1));
        ms.setMerchandiseId(UUID.randomUUID());
        ms.setMerchandiseName(merchandise.getMerchandiseName());
        return merchandiseRepository.save(ms);
    }

    @GetMapping("/find/merchandise/{merchandiseId}")
    public Merchandise findById(@PathVariable String merchandiseId) {
        return merchandiseRepository.findById(merchandiseId).get();
    }

    @GetMapping("/find/merchandise/all")
    public List<Merchandise> findAll(){
        return merchandiseRepository.findAll();
    }

    @DeleteMapping("/delete/merchandise/{merchandiseId}")
    public void deleteMerchandise(@PathVariable String merchandiseId) {
        merchandiseRepository.deleteById(merchandiseId);
    }
}
