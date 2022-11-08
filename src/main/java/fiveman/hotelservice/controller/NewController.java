package fiveman.hotelservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.New;
import fiveman.hotelservice.request.NewRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.NewService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags = "News")
@RestController
@RequestMapping("/api/v1")
public class NewController {
    
    @Autowired
    NewService newService;

    @Autowired
    ModelMapper modelMapper;


    @GetMapping("/new/{id}")
    public ResponseEntity<New> getNewById(@PathVariable("id") long id){
        return new ResponseEntity<>(newService.getNewById(id), HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<List<New>> getNewByType(@RequestParam("type") String type){
        return new ResponseEntity<>(newService.getNewByType(type), HttpStatus.OK);
    }

    
    @GetMapping("/news")
    public ResponseEntity<List<New>> getAllNew(){
        return new ResponseEntity<>(newService.getAllNew(), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<CustomResponseObject> saveNewEntity(@RequestBody @Valid NewRequest newRequest){
        New oNew = modelMapper.map(newRequest, New.class);
        return new ResponseEntity<>(newService.saveNew(oNew), HttpStatus.OK);
    }

    @PutMapping("/new")
    public ResponseEntity<CustomResponseObject> updateNew(@RequestBody @Valid NewRequest newRequest){
        New oNew = modelMapper.map(newRequest, New.class);
        return new ResponseEntity<CustomResponseObject>(newService.updateNew(oNew), HttpStatus.OK);
    }

    @DeleteMapping("/new")
    public ResponseEntity<CustomResponseObject> deleteNew(@RequestParam long id){
        return new ResponseEntity<CustomResponseObject>(newService.deleteNew(id), HttpStatus.OK);
    }

}
