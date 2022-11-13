package fiveman.hotelservice.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ImageRequest {
      private Long id;
      private String pictureDescription;

      @NotBlank(message = "image url are mandatory")
      private String pictureUrl;
      
      @NotBlank(message = "image type are mandatory")
      @Min(value = 5, message = "Picture Type phai co 5 ki tu")
      private String pictureType;
}
