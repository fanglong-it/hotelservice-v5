package fiveman.hotelservice.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Setter
@Getter
public class CustomerInfoMomoRequest {
      private String name;
      private String phoneNumber;
      private String email;
}
