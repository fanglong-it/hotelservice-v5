package fiveman.hotelservice.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequest {

    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int gender;
    private String phoneNumber;
    private String email;
    private int idNo;
    private int passportNo;
    
    private String birthDate;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;
    
}
