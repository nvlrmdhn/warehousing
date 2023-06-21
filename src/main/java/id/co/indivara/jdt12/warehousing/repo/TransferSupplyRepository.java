package id.co.indivara.jdt12.warehousing.repo;

import id.co.indivara.jdt12.warehousing.entity.TransferSupply;
import id.co.indivara.jdt12.warehousing.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferSupplyRepository extends JpaRepository<TransferSupply,String> {
    List<TransferSupply> findByWarehouse(Warehouse warehouse);
}
