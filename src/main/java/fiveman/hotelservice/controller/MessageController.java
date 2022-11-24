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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.Message;
import fiveman.hotelservice.request.MessageRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.MessageResponse;
import fiveman.hotelservice.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Message")
@RequestMapping("/api/v1/")
public class MessageController {
      
      @Autowired
      private MessageService messageService;
      
      @Autowired
      private ModelMapper modelMapper;

      @GetMapping("/messages")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<MessageResponse>> getmessages() {
            return new ResponseEntity<List<MessageResponse>>(messageService.getAllMessage(), HttpStatus.OK);
      }


      @GetMapping("/messagesByBooking")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      public ResponseEntity<List<MessageResponse>> getMessageByBookingId(@RequestParam("booking_id") long id){
            return new ResponseEntity<>(messageService.getAllMessageByBooking_Id(id), HttpStatus.OK);
      }


      @GetMapping("/message/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<MessageResponse> getmessage(@PathVariable("id") long id) {
            return new ResponseEntity<MessageResponse>(messageService.getMessageById(id), HttpStatus.OK);
      }

      @PostMapping("/message")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<MessageResponse>> savemessage(@RequestBody @Valid MessageRequest request) {
            Message message = modelMapper.map(request, Message.class);
            return new ResponseEntity<>(messageService.addMessage(message), HttpStatus.OK);
      }

      @PutMapping("/message")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<MessageResponse>> updatemessage(@RequestBody @Valid MessageRequest request) {
            Message message = modelMapper.map(request, Message.class);
            return new ResponseEntity<>(messageService.updateMessage(message), HttpStatus.OK);
      }

      @DeleteMapping("/message/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<MessageResponse>> deletemessage(@PathVariable long id) {
            return new ResponseEntity<>(messageService.deleteMessage(id), HttpStatus.OK);
      }

}
