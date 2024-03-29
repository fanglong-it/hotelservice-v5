package fiveman.hotelservice.response;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private long id;
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private boolean gender;
    private String phoneNumber;
    private String dateOfBirth;
    private String userRole;
    private String status;
    
    private long hotel_Id;

    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;

}
