package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "service_category")
public class ServiceCategory {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @ApiModelProperty(required = true)
      private long id;
      private String name;
      private String description;

      boolean foodAndBeverage;

      boolean ordered;

      boolean status;
      @ManyToOne(fetch = FetchType.LAZY, targetEntity = Hotel.class)
      @JsonBackReference
      private Hotel hotel;

      @OneToMany(mappedBy = "serviceCategory", fetch = FetchType.LAZY)
      @JsonBackReference
      List<Service> services;

}
