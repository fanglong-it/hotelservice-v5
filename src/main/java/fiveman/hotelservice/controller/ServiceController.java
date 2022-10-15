package fiveman.hotelservice.controller;

import java.util.List;

import javax.validation.Valid;

import fiveman.hotelservice.request.ServiceRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import fiveman.hotelservice.entities.Service;
import fiveman.hotelservice.service.ServiceService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Api(tags = "Service")
@RequestMapping("/api/v1")
public class ServiceController {
    @Autowired
    ServiceService service;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/services")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<List<Service>> getAllServices() {
        return new ResponseEntity<List<Service>>(service.getAllServices(), HttpStatus.OK);
    }

    @GetMapping("/service/{id}")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<Service> getServicesById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getServiceById(id), HttpStatus.OK);
    }

    @GetMapping("/service")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<List<Service>> getAllServicesByServiceCategories(@RequestParam("cate_id") long id) {
        return new ResponseEntity<>(service.getAllServicesByServiceCategory(id), HttpStatus.OK);
    }


    @PutMapping("/service")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<CustomResponseObject> updateService(@RequestBody Service serviceEntity) {
        return new ResponseEntity<>(service.updateService(serviceEntity), HttpStatus.OK);
    }

    @PostMapping("/service")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<CustomResponseObject> saveService(@RequestBody @Valid ServiceRequest serviceRequest){
        Service serviceEntity = modelMapper.map(serviceRequest, Service.class);
        return new ResponseEntity<>(service.saveServices(serviceEntity),HttpStatus.OK);
    }

    @DeleteMapping("/service/{id}")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public ResponseEntity<CustomResponseObject> deleteServiceById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.deleteService(id), HttpStatus.OK);
    }

}
