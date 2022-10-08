package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    private Customer customer;

}
