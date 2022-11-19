package fiveman.hotelservice.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class HotelRequest {
      private long id;
      private String shortName;
      private String fullName;
      private String phoneNumber;

      private String address;
      private String email;
      private String website;
      private String checkInTime;
      private String checkOutTime;
      private boolean breakfast;
      private int totalArea;
      private int totalRoom;
      private String description;
      private String longitude;
      private String latitude;

      private boolean status;

      private String createDate;
      private String updateDate;
      private String createBy;
      private String lastModifyBy;
}
