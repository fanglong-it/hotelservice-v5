package fiveman.hotelservice.controller;

import java.util.List;

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

import fiveman.hotelservice.entities.Utilities;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.UtilitiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Utilities")
public class UtilitiesController {
      
      @Autowired
      private UtilitiesService utilitiesService;

      @GetMapping("/getAllUtilities")
      @PreAuthorize("hasRole('ROLE_USER') or isAnonymous()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<List<Utilities>> getutilities() {
            return new ResponseEntity<>(utilitiesService.getAllUtilities(), HttpStatus.OK);
      }

      @GetMapping("/utilities/{id}")
      @PreAuthorize("hasRole('ROLE_USER') or isAnonymous()")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<Utilities> getUtilitiesById(@PathVariable("id") long id) {
            return new ResponseEntity<>(utilitiesService.getUtilitiesById(id), HttpStatus.OK);
      }

      @PutMapping("/utilities")
      @PreAuthorize("hasRole('ROLE_ADMIN')")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> updateUtilties(@RequestBody Utilities utilities) {
            return new ResponseEntity<>(utilitiesService.updateUtilities(utilities),
                        HttpStatus.OK);
      }

      @PostMapping("/utilities")
      @PreAuthorize("hasRole('ROLE_ADMIN')")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> addUtilities(@RequestBody Utilities utilities) {
            return new ResponseEntity<>(utilitiesService.saveUtilities(utilities),
                        HttpStatus.OK);
      }

      @DeleteMapping("/utilities/{id}")
      @PreAuthorize("hasRole('ROLE_ADMIN')")
      @ApiResponses(value = { //
                  @ApiResponse(code = 400, message = "Something went wrong"), //
                  @ApiResponse(code = 403, message = "Access denied"), //
                  @ApiResponse(code = 500, message = "Expired or invalid JWT token") })
      public ResponseEntity<CustomResponseObject> deleteUtilities(@PathVariable("id") long id) {
            return new ResponseEntity<>(utilitiesService.deleteUtitlies(id), HttpStatus.OK);
      }
}
