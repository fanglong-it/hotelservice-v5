package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;
    private double totalAmount;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;

}
