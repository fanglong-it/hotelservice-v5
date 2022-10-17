package fiveman.hotelservice.controller;

import fiveman.hotelservice.entities.Image;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Image")
@RequestMapping("/api/v1")
public class ImageController {
    @Autowired
    ImageService imageService;


    @GetMapping("/images")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<Image>> getAllImages() {
        return new ResponseEntity<>(imageService.getAllImage(), HttpStatus.OK);
    }

    @GetMapping("/image")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<Image>> getImageByType(@RequestParam("type") String type) {
        return new ResponseEntity<>(imageService.getImageByImageType(type), HttpStatus.OK);
    }

    @GetMapping("/image/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")

    public ResponseEntity<Image> getImageById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(imageService.getImageById(id), HttpStatus.OK);
    }

    @PostMapping("/image")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> saveImage(@RequestBody @Valid Image image) {
        return new ResponseEntity<>(imageService.saveImage(image), HttpStatus.OK);
    }

    @PutMapping("/image")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> updateImage(@Valid @RequestBody Image image) {
        return new ResponseEntity<>(imageService.updateImage(image), HttpStatus.OK);
    }

    @DeleteMapping("/image/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> deleteImage(@PathVariable("id") Long id) {
        return new ResponseEntity<>(imageService.deleteImage(id), HttpStatus.OK);
    }






}
