package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.FeedbackContent;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.FeedBackContentRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.FeedbackContentService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FeedbackContentServiceImpl implements FeedbackContentService {
      
      @Autowired
      private FeedBackContentRepository feedBackContentRepository;
      
      @Override
      public List<FeedbackContent> getAllFeedbackContent() {
            log.info("START OF FIND ALL FEEDBACK CONTENT");
            return feedBackContentRepository.findAll();
      }

      @Override
      public FeedbackContent getFeedbackContent(long id) {
            log.info("START OF GET ROOM TYPE ULTILITIES BY ID");
            if (feedBackContentRepository.existsById(id)) {
                  return feedBackContentRepository.getFeedbackContentById(id);
            }
            log.info("GET FEEDBACK CONTENT FAIL");
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(
                        HttpStatus.NOT_FOUND.toString(), "Not found Feedback content Id = " + id));
      }

      @Override
      public CustomResponseObject saveFeedbackContent(FeedbackContent FeedbackContent) {
            log.info("START SAVE FEEDBACK CONTENT");
            if (feedBackContentRepository.existsById(FeedbackContent.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist Id = " + FeedbackContent.getId()));
            }
            feedBackContentRepository.save(FeedbackContent);
            log.info("END  SAVE FEEDBACK CONTENT");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding success!");
      }

      @Override
      public CustomResponseObject updateFeedbackContent(FeedbackContent FeedbackContent) {
            log.info("START OF UPDATE FEEDBACK CONTENT");
            if (!feedBackContentRepository.existsById(FeedbackContent.getId())) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL,
                              "Not found Id = " + FeedbackContent.getId()));
            }
            feedBackContentRepository.save(FeedbackContent);
            log.info("END OF UPDATE FEEDBACK CONTENT");
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
      }

      @Override
      public CustomResponseObject deleteFeedbackContent(long id) {
            log.info("START OF DELETE FEEDBACK CONTENT");
            if (!feedBackContentRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.DELETE_FAIL, "Not found Id = " + id));
            }
            feedBackContentRepository.deleteById(id);
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
      }

}
