package fiveman.hotelservice.request;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Statistic{
    private String date;
    private double totalPrice;
}
