package fiveman.hotelservice.entities;


import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "picture")
@Data
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "picture type are mandatory")
    @Min(value = 5, message = "Picture Type phai co 5 ki tu")
    private String pictureType;

    private String pictureDescription;
    @NotBlank(message = "picture url are mandatory")
    private String pictureUrl;
}
