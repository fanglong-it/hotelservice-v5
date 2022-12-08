package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.CustomerFeedback;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.CustomerFeedbackResponse;

public interface CustomerFeedbackService {
      List<CustomerFeedbackResponse> getAllCustomerFeedback();

      List<CustomerFeedback> getCustomerFeedBackByBookingId(long booking_id);
            
      CustomerFeedbackResponse getCustomerFeedback(long id);
      
      CustomResponseObject saveCustomerFeedback(CustomerFeedback customerFeedback);
      
      CustomResponseObject updateCustomerFeedback(CustomerFeedback customerFeedback);
      
      CustomResponseObject deleteCustomerFeedback(long id);
}
