package id.co.indivara.jdt12.warehousing.repo;

import id.co.indivara.jdt12.warehousing.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,String> {
}
