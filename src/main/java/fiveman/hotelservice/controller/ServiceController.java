package fiveman.hotelservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/services")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Service>> getAllServices(){
        return new ResponseEntity<List<Service>>(service.getAllServices(), HttpStatus.OK);   
    }

    @GetMapping("/service/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Service> getServicesById(@PathParam("id") Long id){
        return new ResponseEntity<>(service.getServiceById(id), HttpStatus.OK);
    }


    @PutMapping("/service")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Service> updateService(@RequestBody Service serviceEntity){
        return new ResponseEntity<>(service.updateService(serviceEntity),HttpStatus.OK);
    }

    @PostMapping("/service")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Service> saveService(@RequestBody Service serviceEntity){
        return new ResponseEntity<>(service.saveServices(serviceEntity),HttpStatus.OK);
    }
    
    @DeleteMapping("/service/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> deleteServiceById(@PathParam("id") Long id){
        return new ResponseEntity<>(service.deleteService(id), HttpStatus.OK);
    }

}
