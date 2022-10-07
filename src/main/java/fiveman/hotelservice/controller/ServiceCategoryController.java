package fiveman.hotelservice.controller;


import fiveman.hotelservice.entities.Service;
import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.request.ServiceCategoryRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.ServiceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "Service_Category")
@RequestMapping("/api/v1")
public class ServiceCategoryController {
    @Autowired
    ServiceCategoryService serviceCategoryService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/serviceCategories")
    @PreAuthorize("isAnonymous() or hasRole('ROLE_USER')")
    public ResponseEntity<List<ServiceCategory>> getServiceCategories(){
        return new ResponseEntity<>(serviceCategoryService.getServiceCategories(), HttpStatus.OK);
    }

    @GetMapping("/serviceCategory/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ServiceCategory> getServiceCategory(@PathVariable("id") long id){
        return new ResponseEntity<>(serviceCategoryService.getServiceCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/serviceCategory")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<CustomResponseObject> saveServiceCategory(@RequestBody @Valid ServiceCategoryRequest serviceCategoryRequest){
       ServiceCategory serviceCategory = modelMapper.map(serviceCategoryRequest, ServiceCategory.class);
        return new ResponseEntity<>(serviceCategoryService.saveServiceCategory(serviceCategory), HttpStatus.OK);
    }


}
