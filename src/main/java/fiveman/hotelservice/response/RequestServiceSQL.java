package fiveman.hotelservice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestServiceSQL {
    private long id;
    private String name;
    private int quantity;
    private double price;
    private double totalPrice;
    
}
