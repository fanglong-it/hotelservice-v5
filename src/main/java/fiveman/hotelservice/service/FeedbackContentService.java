package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.FeedbackContent;
import fiveman.hotelservice.response.CustomResponseObject;

public interface FeedbackContentService {
      List<FeedbackContent> getAllFeedbackContent();
      
      FeedbackContent getFeedbackContent(long id);
      
      CustomResponseObject saveFeedbackContent(FeedbackContent FeedbackContent);
      
      CustomResponseObject updateFeedbackContent(FeedbackContent FeedbackContent);

      CustomResponseObject deleteFeedbackContent(long id);
}
