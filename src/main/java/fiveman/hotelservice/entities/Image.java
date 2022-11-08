package fiveman.hotelservice.entities;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "image")
@Data
public class Image {
      
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "image type are mandatory")
    @Min(value = 5, message = "Picture Type phai co 5 ki tu")
    private String pictureType;

    private String pictureDescription;

    @NotBlank(message = "image url are mandatory")
    private String pictureUrl;
}
