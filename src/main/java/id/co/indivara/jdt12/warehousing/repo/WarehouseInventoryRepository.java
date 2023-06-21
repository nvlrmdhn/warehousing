package id.co.indivara.jdt12.warehousing.repo;

import id.co.indivara.jdt12.warehousing.entity.Merchandise;
import id.co.indivara.jdt12.warehousing.entity.Warehouse;
import id.co.indivara.jdt12.warehousing.entity.WarehouseInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseInventoryRepository extends JpaRepository<WarehouseInventory,String> {

    WarehouseInventory findByMerchandiseCodeAndWarehouseCode(Merchandise merchandise, Warehouse warehouse);
    List<WarehouseInventory> findByWarehouseCode(Warehouse warehouse);
    List<WarehouseInventory> findByMerchandiseCode(Merchandise merchandise);

}
