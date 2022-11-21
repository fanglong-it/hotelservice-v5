package fiveman.hotelservice.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "roomType")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomType implements Serializable{

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @ApiModelProperty(required = true)
      @Column(name = "id")
      private long id;
      private String name;
      private String description;
      private int maxOccupancy;
      private int maxAdult;
      private int maxChildren;
      private int defaultOccupancy;
      private boolean isActive;
      private double defaultPrice;

      
      @OneToMany(mappedBy = "roomType")
      @JsonManagedReference
      private List<RoomPrice> roomPrices;

      @OneToMany(mappedBy = "roomType")
      @JsonManagedReference
      private List<RoomTypeUtilities> roomTypeUtilities;
      
      @OneToMany(mappedBy = "roomType")
      @JsonManagedReference
      private List<Room> rooms;
      
}
