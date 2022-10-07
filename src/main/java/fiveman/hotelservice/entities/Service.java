package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    private String name;
    private String picture;
    private double price;
    private String description;

    private boolean isExternal;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,
    cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private ServiceCategory serviceCategory;
}
