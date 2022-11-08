package fiveman.hotelservice.entities;

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
@Table(name = "RequestService")
public class RequestService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String requestServiceName;
    private String requestServiceType;
    private String dateTime;
    private String status;

    
    @ManyToOne(fetch = FetchType.EAGER)
    private Booking booking;

}
