package fiveman.hotelservice.entities;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;

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

   private boolean status;

   @ManyToOne(fetch = FetchType.EAGER, targetEntity = Hotel.class)
   private Hotel hotel;


}
