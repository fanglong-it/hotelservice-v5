package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fiveman.hotelservice.entities.FeedbackContent;

public interface FeedBackContentRepository extends JpaRepository<FeedbackContent, Long>{
      FeedbackContent getFeedbackContentById(long id);
}
