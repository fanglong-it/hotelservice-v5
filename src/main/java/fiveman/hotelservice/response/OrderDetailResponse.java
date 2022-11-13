package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.Service;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetailResponse {
    private long id;
    private Service service;
    private long order_Id;
    private int quantity;
    private double price;
    private double amount;
    private String orderDate;
}
