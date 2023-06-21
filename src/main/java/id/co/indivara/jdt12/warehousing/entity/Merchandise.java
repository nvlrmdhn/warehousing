package id.co.indivara.jdt12.warehousing.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "merchandises")
public class Merchandise {

    @Id
    @Column(name = "merchandise_code",nullable = false)
    private String merchandiseCode;

    @Column(name = "merchandise_id",nullable = false, length = 32)
    private UUID merchandiseId;

    @Column(name = "merchandise_name",nullable = false,length = 50)
    private String merchandiseName;
}
