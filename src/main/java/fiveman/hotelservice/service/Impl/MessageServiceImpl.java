package fiveman.hotelservice.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.Message;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.MessageRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.MessageResponse;
import fiveman.hotelservice.service.MessageService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

      @Autowired
      private MessageRepository messageRepository;

      @Autowired
      ModelMapper modelMapper;

      public MessageResponse mappMessageToResponse(Message message) {
            MessageResponse messageResponse = modelMapper.map(message, MessageResponse.class);
            messageResponse.getBooking().setHotel(null);
            return messageResponse;
      }

      @Override
      public List<MessageResponse> getAllMessageByBooking_Id(long id) {
            List<MessageResponse> messageResponses = new ArrayList<>();

            List<Message> messages = messageRepository.getAllMessageByBooking_Id(id);
            for (Message message : messages) {
                  MessageResponse messageResponse = mappMessageToResponse(message);
                  messageResponses.add(messageResponse);
            }
            return messageResponses;
      }

      @Override
      public List<MessageResponse> getAllMessage() {
            log.info("GET ALL MESSAGES");
            // return messageRepository.findAll();

            List<MessageResponse> messageResponses = new ArrayList<>();

            List<Message> messages = messageRepository.findAll();
            for (Message message : messages) {
                  MessageResponse messageResponse = mappMessageToResponse(message);
                  messageResponses.add(messageResponse);
            }
            return messageResponses;
      }

      @Override
      public MessageResponse getMessageById(long id) {
            log.info("START GET MESSAGE BY ID");
            if (!messageRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
            }
            log.info("END GET MESSAGE BY ID");
            return mappMessageToResponse(messageRepository.getMessageById(id));
      }

      @Override
      public List<MessageResponse> addMessage(Message message) {
            log.info("START SAVE MESSAGE");
            if (messageRepository.existsById(message.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + message.getId()));
            }
            messageRepository.save(message);
            log.info("END SAVE MESSAGE");
            // return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding MESSAGE
            // Success!");
            return getAllMessage();
      }

      @Override
      public List<MessageResponse> updateMessage(Message message) {
            log.info("START UPDATE MESSAGE");
            if (messageRepository.existsById(message.getId())) {
                  messageRepository.save(message);
                  log.info("END UPDATE MESSAGE");
                  // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
                  return getAllMessage();
            }
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + message.getId()));

      }

      @Override
      public List<MessageResponse> deleteMessage(long id) {
            if (messageRepository.existsById(id)) {
                  log.info("DELETE MESSAGE");
                  messageRepository.deleteById(id);
                  // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
                  return getAllMessage();

            }
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));

      }

}
