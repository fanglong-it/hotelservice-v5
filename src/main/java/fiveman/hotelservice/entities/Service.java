package fiveman.hotelservice.entities;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


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
    @NotBlank(message = "picture are mandatory")
    private String picture;
    @NotBlank(message = "price are mandatory")
    private double price;
    @NotBlank(message = "description are mandatory")
    private String description;

    @ApiModelProperty(required = true)
    private boolean isExternal;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String updateBy;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ServiceCategory.class)
    private ServiceCategory serviceCategory;
}
