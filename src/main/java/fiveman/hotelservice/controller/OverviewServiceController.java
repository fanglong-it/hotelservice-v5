package fiveman.hotelservice.controller;

import java.util.List;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import fiveman.hotelservice.entities.OverviewService;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.OverviewServiceService;
import fiveman.hotelservice.utils.Common;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "overview")
@RequestMapping("/api/v1/")
public class OverviewServiceController {

	@Autowired
	private OverviewServiceService overviewServiceService;
	
	@GetMapping("/overview/getOverviewServices")
	@PreAuthorize("hasRole('ROLE_USER')")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<List<OverviewService>> getOverviewServices(){
		return new ResponseEntity<List<OverviewService>>(overviewServiceService.getAllOverviewService(), HttpStatus.OK);	
	}
	
	
	@GetMapping("/overview/getOverviewService/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or isAnonymous()")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<OverviewService> getOverviewService(@PathVariable("id") long id){
		return new ResponseEntity<OverviewService>(overviewServiceService.getOverviewService(id), HttpStatus.OK);
	}

	@PostMapping("/overview/addOverviewService")
	@ApiModelProperty(required = true, name = "id")
	@PreAuthorize("hasRole('ROLE_USER') or isAnonymous()")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<CustomResponseObject> addOverviewService(@RequestBody OverviewService overviewService){
		overviewServiceService.addOverviewService(overviewService);
		return new ResponseEntity<CustomResponseObject>(new CustomResponseObject(Common.ADDING_SUCCESS, "Add Overview success" ), HttpStatus.OK);
	}

	@PostMapping("/overview/updateOverviewService")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<CustomResponseObject> updateOverviewService(@RequestBody OverviewService overviewService){
		overviewServiceService.updateOverviewService(overviewService);
		return new ResponseEntity<CustomResponseObject>(new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Overview success" ), HttpStatus.OK);
	}

	@DeleteMapping("/overview/deleteOverview/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiResponses(value = {//
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token")})
	public ResponseEntity<CustomResponseObject> deleteOverviewService(@PathVariable("id") long id){
		overviewServiceService.deleteOverviewService(id);
		return new ResponseEntity<CustomResponseObject>(new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Overview success" ), HttpStatus.OK);
	}

}























