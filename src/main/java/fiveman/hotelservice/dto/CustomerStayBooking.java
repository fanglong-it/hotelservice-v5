package fiveman.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerStayBooking {

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

    private String isPrimary;

}
