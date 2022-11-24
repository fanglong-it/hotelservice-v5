package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Message;
import fiveman.hotelservice.response.MessageResponse;

public interface MessageService {
      List<MessageResponse> getAllMessage();
      List<MessageResponse> getAllMessageByBooking_Id(long id);      
      MessageResponse getMessageById(long id);
      
      List<MessageResponse> addMessage(Message message);
      
      List<MessageResponse> updateMessage(Message message);
      
      List<MessageResponse> deleteMessage(long id);
}
