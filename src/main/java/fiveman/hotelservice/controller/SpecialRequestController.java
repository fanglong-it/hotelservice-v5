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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.SpecialRequest;
import fiveman.hotelservice.request.SpecialRequestRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.SpecialRequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Special_Request")
@RequestMapping("/api/v1")
public class SpecialRequestController {
      
      @Autowired
      private SpecialRequestService specialRequestService;
      
      @Autowired
      private ModelMapper modelMapper;

      @GetMapping("/specialRequests")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<SpecialRequest>> getSpecialRequests() {
            return new ResponseEntity<List<SpecialRequest>>(specialRequestService.getAllSpecialRequest(), HttpStatus.OK);
      }

      @GetMapping("/specialRequest/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<SpecialRequest> getSpecialRequest(@PathVariable("id") long id) {
            return new ResponseEntity<SpecialRequest>(specialRequestService.getSpecialRequest(id), HttpStatus.OK);
      }

      @PostMapping("/specialRequest")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> saveSpecialRequest(@RequestBody @Valid SpecialRequestRequest specialRequestRequest) {
            SpecialRequest specialRequest = modelMapper.map(specialRequestRequest, SpecialRequest.class);
            return new ResponseEntity<>(specialRequestService.saveSpecialRequest(specialRequest), HttpStatus.OK);
      }

      @PutMapping("/specialRequest")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> updateSpecialRequest(@RequestBody @Valid SpecialRequestRequest specialRequestRequest) {
            SpecialRequest specialRequest = modelMapper.map(specialRequestRequest, SpecialRequest.class);
            return new ResponseEntity<>(specialRequestService.updateSpecialRequest(specialRequest), HttpStatus.OK);
      }

      @DeleteMapping("/specialRequest/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> deleteSpecialRequest(@PathVariable long id) {
            return new ResponseEntity<>(specialRequestService.deleteSpecialRequest(id), HttpStatus.OK);
      }
}   
