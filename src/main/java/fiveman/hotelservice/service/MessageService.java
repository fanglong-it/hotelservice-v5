package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Message;
import fiveman.hotelservice.response.CustomResponseObject;

public interface MessageService {
      List<Message> getAllMessage();

      List<Message> getAllMessageByBooking_Id(long id);

      Message getMessageById(long id);

      Message addMessage(Message message);

      Message updateMessage(Message message);

      CustomResponseObject deleteMessage(long id);
}
