package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;
    @NotBlank(message = "Name are mandatory")
    private String name;

    @NotBlank(message = "price are mandatory")
    private double price;

    @NotBlank(message = "description are mandatory")
    private String description;

    private boolean status;

    private String majorGroup;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ServiceCategory.class)
    private ServiceCategory serviceCategory;

}
