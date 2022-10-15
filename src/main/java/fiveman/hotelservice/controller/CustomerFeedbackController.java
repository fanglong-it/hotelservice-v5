package fiveman.hotelservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.CustomerFeedback;
import fiveman.hotelservice.request.CustomerFeedbackRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.CustomerFeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "Customer_Feedback")
@RequestMapping("/api/v1")
public class CustomerFeedbackController {
      
      @Autowired
      private CustomerFeedbackService customerFeedbackService;
      

      @Autowired
      private ModelMapper modelMapper;
      
      @GetMapping("/customerFeedBacks")
      @PreAuthorize("hasRole('ROLE_ADMIN')")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<CustomerFeedback>> getCustomerFeedBacks() {
            return new ResponseEntity<List<CustomerFeedback>>(customerFeedbackService.getAllCustomerFeedback(), HttpStatus.OK);
      }

      @GetMapping("/customerFeedBack/{id}")
      @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomerFeedback> getCustomerFeedBack(@PathVariable("id") long id) {
            return new ResponseEntity<CustomerFeedback>(customerFeedbackService.getCustomerFeedback(id), HttpStatus.OK);
      }

      @PostMapping("/customerFeedBack")
      @PreAuthorize("hasRole('ROLE_ADMIN')")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> savecustomerFeedBack(@RequestBody @Valid CustomerFeedbackRequest request) {
            CustomerFeedback customerFeedBack = modelMapper.map(request, CustomerFeedback.class);
            return new ResponseEntity<>(customerFeedbackService.saveCustomerFeedback(customerFeedBack), HttpStatus.OK);
      }

      @PutMapping("/customerFeedBack")
      @PreAuthorize("hasRole('ROLE_ADMIN')")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> updatecustomerFeedBack(@RequestBody @Valid CustomerFeedbackRequest request) {
            CustomerFeedback customerFeedBack = modelMapper.map(request, CustomerFeedback.class);
            return new ResponseEntity<>(customerFeedbackService.updateCustomerFeedback(customerFeedBack), HttpStatus.OK);
      }

      @DeleteMapping("/customerFeedBack/{id}")
      @PreAuthorize("hasRole('ROLE_ADMIN')")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> deletecustomerFeedBack(@PathVariable long id) {
            return new ResponseEntity<>(customerFeedbackService.deleteCustomerFeedback(id), HttpStatus.OK);
      }

}
