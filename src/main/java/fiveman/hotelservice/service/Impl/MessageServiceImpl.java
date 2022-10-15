package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.Message;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.MessageRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.MessageService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService{
      
      @Autowired
      private MessageRepository messageRepository;
      
      @Override
      public List<Message> getAllMessage() {
            log.info("GET ALL MESSAGES");
            return messageRepository.findAll();
      }

      @Override
      public Message getMessageById(long id) {
            log.info("START GET MESSAGE BY ID");
            if (!messageRepository.existsById(id)) {
                throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
            }
            log.info("END GET MESSAGE BY ID");
            return messageRepository.getMessageById(id);
      }

      @Override
      public CustomResponseObject addMessage(Message message) {
            log.info("START SAVE MESSAGE");
            if (messageRepository.existsById(message.getId())) {
                throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + message.getId()));
            }
            messageRepository.save(message);
            log.info("END SAVE MESSAGE");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding MESSAGE Success!");
      }

      @Override
      public CustomResponseObject updateMessage(Message message) {
            log.info("START UPDATE MESSAGE");
            if (messageRepository.existsById(message.getId())) {
                messageRepository.save(message);
                log.info("END UPDATE MESSAGE");
                return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
            }
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + message.getId()));
      }

      @Override
      public CustomResponseObject deleteMessage(long id) {
            if (messageRepository.existsById(id)) {
                  log.info("DELETE MESSAGE");
                  messageRepository.deleteById(id);
                  return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
              }
              throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
      }

}
