package id.co.indivara.jdt12.warehousing.repo;

import id.co.indivara.jdt12.warehousing.entity.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise, String> {
}
