package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "roomType")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;
    private String name;
    private String description;
    private int maxOccupancy;
    private int maxAdult;
    private int maxChildren;
    private int defaultOccupancy;
    @NonNull
    private boolean isActive;

}
