package fiveman.hotelservice.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(required = true)
    private long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private int gender;
    private String phoneNumber;
    private String email;
    private int idNo;
    private int passportNo;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String lastModifyBy;

}
