package fiveman.hotelservice.entities;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "hotel")
public class Hotel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;
    private String name;
    private String phoneNumber;
    private String description;
    private String longtidude;
    private String latitude;


    @ManyToMany(fetch = FetchType.LAZY,targetEntity = Location.class)
    @JoinColumn(name = "description")
    private List<Location> locations;

}
