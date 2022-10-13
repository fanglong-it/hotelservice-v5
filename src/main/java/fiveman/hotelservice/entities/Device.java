package fiveman.hotelservice.entities;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "devices")
public class Device implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6208328601953313129L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private Long id;

    @NotBlank
    private String partNumber;
    
    @NotBlank
    private String serialNo;
    
    private String name;
    private String brand;
    private String description;
    
    @NonNull
    private boolean status;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    private Room room;
}
