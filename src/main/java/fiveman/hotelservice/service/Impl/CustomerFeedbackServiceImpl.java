package fiveman.hotelservice.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.CustomerFeedback;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.CustomerFeedbackRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.CustomerFeedbackResponse;
import fiveman.hotelservice.service.CustomerFeedbackService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerFeedbackServiceImpl implements CustomerFeedbackService {

    @Autowired
    private CustomerFeedbackRepository customerFeedbackRepository;


    @Autowired
    ModelMapper modelMapper;

    public CustomerFeedbackResponse mapCustomerFeedBackToResponse(CustomerFeedback customerFeedback) {
        CustomerFeedbackResponse customerFeedbackResponse = modelMapper.map(customerFeedback, CustomerFeedbackResponse.class);
        customerFeedbackResponse.setBooking_Id(customerFeedback.getBooking().getId());
        return customerFeedbackResponse;
    }


    @Override
    public List<CustomerFeedback> getCustomerFeedBackByBookingId(long booking_id) {
        return customerFeedbackRepository.getCustomerFeedbackByBookingId(booking_id);
    }


    @Override
    public List<CustomerFeedbackResponse> getAllCustomerFeedback() {
        log.info("START GET ALL CUSTOMER FEEDBACK");

        List<CustomerFeedback> customerFeedbacks = customerFeedbackRepository.findAll();
        List<CustomerFeedbackResponse> customerFeedbackResponses = new ArrayList<>();
        for (CustomerFeedback customerFeedback : customerFeedbacks) {
            CustomerFeedbackResponse customerFeedbackResponse = mapCustomerFeedBackToResponse(customerFeedback);
            customerFeedbackResponses.add(customerFeedbackResponse);
        }
        return customerFeedbackResponses;
    }

      @Override
      public CustomerFeedbackResponse getCustomerFeedback(long id) {
            log.info("START GET ALL CUSTOMER FEEDBACK BY ID");
            if (!customerFeedbackRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
            }

            log.info("END GET ALL CUSTOMER FEEDBACK BY ID");
            return mapCustomerFeedBackToResponse(customerFeedbackRepository.getCustomerFeedbackById(id));
      }

      @Override
      public CustomResponseObject saveCustomerFeedback(CustomerFeedback customerFeedback) {
            log.info("START SAVE ALL CUSTOMER FEEDBACK ");
            if (customerFeedbackRepository.existsById(customerFeedback.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + customerFeedback.getId()));
            }
            customerFeedbackRepository.save(customerFeedback);
            log.info("END SAVE ALL CUSTOMER FEEDBACK ");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
      }

      @Override
      public CustomResponseObject updateCustomerFeedback(CustomerFeedback customerFeedback) {
            log.info("START UPDATE ALL CUSTOMER FEEDBACK ");
            if (!customerFeedbackRepository.existsById(customerFeedback.getId())) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL,
                              "Not found id =" + customerFeedback.getId()));
            }
            customerFeedbackRepository.save(customerFeedback);
            log.info("END UPDATE ALL CUSTOMER FEEDBACK ");
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
      }

      @Override
      public CustomResponseObject deleteCustomerFeedback(long id) {
            log.info("START DELETE ALL CUSTOMER FEEDBACK ");
            if (!customerFeedbackRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
            }
            customerFeedbackRepository.deleteById(id);
            log.info("END DELETE ALL CUSTOMER FEEDBACK ");
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
      }

}
