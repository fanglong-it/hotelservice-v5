package fiveman.hotelservice.response;

import fiveman.hotelservice.entities.FeedbackContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerFeedbackResponse {
    private long id;
    private long booking_Id;
    private FeedbackContent feedbackContent;
    private int rating;
    private String dateTime;
}
