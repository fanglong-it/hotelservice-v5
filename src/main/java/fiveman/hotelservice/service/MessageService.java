package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Message;
import fiveman.hotelservice.response.CustomResponseObject;

public interface MessageService {
      List<Message> getAllMessage();
      
      Message getMessageById(long id);
      
      CustomResponseObject addMessage(Message message);
      
      CustomResponseObject updateMessage(Message message);
      
      CustomResponseObject deleteMessage(long id);
}
