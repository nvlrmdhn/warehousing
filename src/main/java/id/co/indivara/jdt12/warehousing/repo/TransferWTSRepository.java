package id.co.indivara.jdt12.warehousing.repo;

import id.co.indivara.jdt12.warehousing.entity.TransferWTS;
import id.co.indivara.jdt12.warehousing.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferWTSRepository extends JpaRepository<TransferWTS,String> {

    List<TransferWTS> findBySource(Warehouse warehouse);

}
