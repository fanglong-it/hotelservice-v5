package fiveman.hotelservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.FeedbackContent;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.FeedbackContentService;
import io.swagger.annotations.Api;

@RestController
@Api(tags = "Feedback_Content")
@RequestMapping("/api/v1")
public class FeedbackContentController {
    
    @Autowired
    FeedbackContentService feedbackContentService;


    @GetMapping("/feedbackContent/{id}")
    public ResponseEntity<FeedbackContent> getFeedbackContent(@PathVariable("id") long id){
        return new ResponseEntity<FeedbackContent>(feedbackContentService.getFeedbackContent(id), HttpStatus.OK);
    }


    @GetMapping("/feedbackContents")
    public ResponseEntity<List<FeedbackContent>> getAllFeedbackContent(){
        return new ResponseEntity<>(feedbackContentService.getAllFeedbackContent(), HttpStatus.OK);
    }


    @PostMapping("/feedbackContent")
    public ResponseEntity<CustomResponseObject> saveFeedbackContent(@RequestBody @Valid FeedbackContent feedbackContent){
        return new ResponseEntity<>(feedbackContentService.saveFeedbackContent(feedbackContent), HttpStatus.OK);
    }

    @PutMapping("/feedbackContent")
    public ResponseEntity<CustomResponseObject> updateFeedbackContent(@RequestBody @Valid FeedbackContent feedbackContent){
        return new ResponseEntity<CustomResponseObject>(feedbackContentService.updateFeedbackContent(feedbackContent), HttpStatus.OK);
    }

    @DeleteMapping("/feedbackContent/{id}")
    public ResponseEntity<CustomResponseObject> deleteFeedbackContent(@PathVariable("id") long id){
        return new ResponseEntity<>(feedbackContentService.deleteFeedbackContent(id), HttpStatus.OK);
    }
}
