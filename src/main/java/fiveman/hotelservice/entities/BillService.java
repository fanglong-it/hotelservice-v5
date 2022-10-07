package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "bill_service")
public class BillService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bill.class)
    private Bill bill;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Service.class)
    private Service service;

    private int quantity;
}
