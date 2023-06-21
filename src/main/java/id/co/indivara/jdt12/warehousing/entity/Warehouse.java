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
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @Column(name = "warehouse_code",nullable = false)
    private String warehouseCode;

    @Column(name = "warehouse_id",nullable = false,length = 32)
    private UUID warehouseId;

    @Column(name = "warehouse_name",nullable = false,length = 50)
    private String warehouseName;

    @Column(name = "warehouse_location",nullable = false,length = 50)
    private String warehouseLocation;

    @Column(name = "join_date",nullable = false)
    private Timestamp joinDate;
}

