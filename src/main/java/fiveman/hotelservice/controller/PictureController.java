package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Picture;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Picture")
@RequestMapping("/api/v1")
public class PictureController {
    @Autowired
    PictureService pictureService;


    @GetMapping("/pictures")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<Picture>> getAllPictures(){
        return new ResponseEntity<>(pictureService.getAllPicture(), HttpStatus.OK);
    }

    @GetMapping("/picture/{type}")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<List<Picture>> getPictureByType(@PathVariable("type") String type){
        return new ResponseEntity<>(pictureService.getPictureByPictureType(type), HttpStatus.OK);
    }

    @GetMapping("/picture/{id}")
    @PreAuthorize("isAnonymous()")

    public ResponseEntity<Picture> getPictureById(@PathVariable("id") Long id){
        return new ResponseEntity<>(pictureService.getPictureById(id), HttpStatus.OK);
    }

    @PostMapping("/picture")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<CustomResponseObject> savePicture(@RequestBody @Valid Picture picture){
        return new ResponseEntity<>(pictureService.savePicture(picture), HttpStatus.OK);
    }

    @PutMapping("/picture")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<CustomResponseObject> updatePicture(@Valid @RequestBody Picture picture){
        return new ResponseEntity<>(pictureService.updatePicture(picture), HttpStatus.OK);
    }

    @DeleteMapping("/picture/{id}")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<CustomResponseObject> deletePicture(@PathVariable("id") Long id){
        return new ResponseEntity<>(pictureService.deletePicture(id), HttpStatus.OK);
    }






}
