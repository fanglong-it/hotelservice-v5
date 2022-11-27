package fiveman.hotelservice.entities;

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
      private int defaultBookingRoom;
      private String code;
      private String bedType;
      private int numOfRoom;

      @OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY)
      @JsonManagedReference
      private List<RoomPrice> roomPrices;

      @OneToMany(mappedBy = "roomType" , fetch = FetchType.LAZY)
      @JsonManagedReference
      private List<RoomTypeUtilities> roomTypeUtilities;
      
      @OneToMany(mappedBy = "roomType", fetch = FetchType.LAZY)
      @JsonManagedReference
      private List<Room> rooms;
      
}
