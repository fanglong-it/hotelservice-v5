package fiveman.hotelservice.request;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Statistic{
    private String date;
    private double totalPrice;
}
