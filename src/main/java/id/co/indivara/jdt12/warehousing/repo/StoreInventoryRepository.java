package id.co.indivara.jdt12.warehousing.repo;

import id.co.indivara.jdt12.warehousing.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreInventoryRepository extends JpaRepository<StoreInventory,String> {
    StoreInventory findByMerchandiseCodeAndStoreCode(Merchandise merchandise, Store store);

    List<StoreInventory> findByStoreCode(Store store);
    List<StoreInventory> findByMerchandiseCode(Merchandise merchandise);

}
