package id.co.indivara.jdt12.warehousing.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "store")
public class Store {

    @Id
    @Column(name = "store_code",nullable = false)
    private String storeCode;

    @Column(name = "store_id",nullable = false, length = 32)
    private UUID storeId;

    @Column(name = "store_name",nullable = false,length = 50)
    private String storeName;

    @Column(name = "store_location",nullable = false,length = 50)
    private String storeLocation;

    @Column(name = "join_date",nullable = false)
    private Timestamp joinDate;
}
