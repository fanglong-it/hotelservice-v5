package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.CustomerFeedback;
import fiveman.hotelservice.response.CustomResponseObject;

public interface CustomerFeedbackService {
      List<CustomerFeedback> getAllCustomerFeedback();
      
      CustomerFeedback getCustomerFeedback(long id);
      
      CustomResponseObject saveCustomerFeedback(CustomerFeedback customerFeedback);
      
      CustomResponseObject updateCustomerFeedback(CustomerFeedback customerFeedback);
      
      CustomResponseObject deleteCustomerFeedback(long id);
}
