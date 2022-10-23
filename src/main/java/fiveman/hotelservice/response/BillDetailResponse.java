package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.Service;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillDetailResponse {
    private long id;
    private long service_Id;
    private long bill_Id;
    private int quantity;
    private double price;
    private double amount;
    private int status;
    private String billDate;
}
