package id.co.indivara.jdt12.warehousing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transfer_supply")
public class TransferSupply {

    @Id
    @Column(name = "trx_supply_code",nullable = false)
    private String trxSupplyCode;

    @Column(name = "trx_supply_id",nullable = false,length = 32)
    private UUID trxSupplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchandise_code",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Merchandise merchandise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Warehouse warehouse;

    @Column(name = "stock",nullable = false)
    private Integer stock;

    @Column(name = "timestamp",nullable = false)
    private Timestamp timestamp;


}
