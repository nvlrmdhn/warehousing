package id.co.indivara.jdt12.warehousing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "store_inventory")
public class StoreInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_code",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Store storeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchandise_code",nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Merchandise merchandiseCode;

    @Column(name = "stock",nullable = false)
    private Integer stock;

}
