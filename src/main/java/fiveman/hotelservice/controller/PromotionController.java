package fiveman.hotelservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.Promotion;
import fiveman.hotelservice.request.PromotionRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.PromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "promotion")
@RequestMapping("/api/v1/")
public class PromotionController {

      @Autowired
      private PromotionService promotionService;

      @Autowired
      private ModelMapper modelMapper;

      @GetMapping("/promotions")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<Promotion>> getpromotions() {
            return new ResponseEntity<List<Promotion>>(promotionService.getPromotions(), HttpStatus.OK);
      }

      @GetMapping("/promotion/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<Promotion> getpromotion(@PathVariable("id") long id) {
            return new ResponseEntity<Promotion>(promotionService.getPromotion(id), HttpStatus.OK);
      }

      @PostMapping("/promotion")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> savepromotion(@RequestBody @Valid PromotionRequest request) {
            Promotion promotion = modelMapper.map(request, Promotion.class);
            return new ResponseEntity<>(promotionService.savePromotion(promotion), HttpStatus.OK);
      }

      @PutMapping("/promotion")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> updatepromotion(@RequestBody @Valid PromotionRequest request) {
            Promotion promotion = modelMapper.map(request, Promotion.class);
            return new ResponseEntity<>(promotionService.updatePromotion(promotion), HttpStatus.OK);
      }

      @DeleteMapping("/promotion/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> deletepromotion(@PathVariable long id) {
            return new ResponseEntity<>(promotionService.deletePromotion(id), HttpStatus.OK);
      }
}
