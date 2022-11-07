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

import fiveman.hotelservice.entities.RequestService;
import fiveman.hotelservice.request.RequestServiceRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RequestServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "Request_Service")
@RequestMapping("/api/v1/")
public class RequestServiceController {
      
      @Autowired
      private RequestServiceService requestServiceService;
      
      @Autowired
      private ModelMapper modelMapper;
      
      
      @GetMapping("/requestrequestServiceServices")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<RequestService>> getRequestServices() {            
            return new ResponseEntity<List<RequestService>>(requestServiceService.getAllRequestService(), HttpStatus.OK);
      }

      @GetMapping("/requestService/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<RequestService> getRequestService(@PathVariable("id") long id) {
            return new ResponseEntity<RequestService>(requestServiceService.getRequestService(id), HttpStatus.OK);
      }

      @PostMapping("/requestService")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> saveRequestService(@RequestBody @Valid RequestServiceRequest request) {
            RequestService requestService = modelMapper.map(request, RequestService.class);
            return new ResponseEntity<>(requestServiceService.saveRequestService(requestService), HttpStatus.OK);
      }

      @PutMapping("/requestService")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> updateRequestService(@RequestBody @Valid RequestServiceRequest request) {
            RequestService requestService = modelMapper.map(request, RequestService.class);
            return new ResponseEntity<>(requestServiceService.updateRequestService(requestService), HttpStatus.OK);
      }

      @DeleteMapping("/requestService/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> deleteRequestService(@PathVariable long id) {
            return new ResponseEntity<>(requestServiceService.deleteRequestService(id), HttpStatus.OK);
      }

}
