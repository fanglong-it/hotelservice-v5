package fiveman.hotelservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiveman.hotelservice.entities.SpecialUtility;
import fiveman.hotelservice.service.SpecialUtilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "Special_Utility")
@RequestMapping("/api/v1")
public class SpecialUilityController {

      @Autowired
      private SpecialUtilityService specialUtilityService;

      @GetMapping("/specialUtilitys")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<SpecialUtility>> getSpecialUtilitys() {
            return new ResponseEntity<List<SpecialUtility>>(specialUtilityService.getAllSpecialUtility(),
                        HttpStatus.OK);
      }

      @GetMapping("/specialUtility/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<SpecialUtility> getSpecialUtility(@PathVariable("id") long id) {
            return new ResponseEntity<SpecialUtility>(specialUtilityService.getSpecialUtility(id), HttpStatus.OK);
      }

      @PostMapping("/specialUtility")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<SpecialUtility> saveSpecialUtility(@RequestBody @Valid SpecialUtility specialUtility) {
            return new ResponseEntity<>(specialUtilityService.saveSpecialUtility(specialUtility), HttpStatus.OK);
      }

      @PutMapping("/specialUtility")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<SpecialUtility> updateSpecialUtility(@RequestBody @Valid SpecialUtility specialUtility) {
            return new ResponseEntity<>(specialUtilityService.updateSpecialUtility(specialUtility), HttpStatus.OK);
      }

      @DeleteMapping("/specialUtility/{id}")
      @PreAuthorize("isAnonymous() or isAuthenticated()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<SpecialUtility> deleteSpecialUtility(@PathVariable long id) {
            return new ResponseEntity<>(specialUtilityService.deleteSpecialUtility(id), HttpStatus.OK);
      }
}
