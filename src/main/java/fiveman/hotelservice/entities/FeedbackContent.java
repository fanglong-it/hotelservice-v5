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
@Table(name = "Feedback_Content")
public class FeedbackContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;


}
