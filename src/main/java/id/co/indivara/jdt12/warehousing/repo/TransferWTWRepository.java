package id.co.indivara.jdt12.warehousing.repo;

import id.co.indivara.jdt12.warehousing.entity.TransferWTW;
import id.co.indivara.jdt12.warehousing.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferWTWRepository extends JpaRepository<TransferWTW,String> {

    List<TransferWTW> findByWarehouseSource(Warehouse warehouse);

}
