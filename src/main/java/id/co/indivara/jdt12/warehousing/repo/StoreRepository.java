package id.co.indivara.jdt12.warehousing.repo;

import id.co.indivara.jdt12.warehousing.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,String> {
}
