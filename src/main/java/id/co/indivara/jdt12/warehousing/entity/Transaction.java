package id.co.indivara.jdt12.warehousing.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transaction_code",nullable = false)
    private String transactionCode;

    @Column(name = "transaction_id",nullable = false,length = 32)
    private UUID transactionId;

    @Column(name = "type",nullable = false)
    private String type;

    @Column(name = "timestamp",nullable = false)
    private Timestamp timestamp;
}
