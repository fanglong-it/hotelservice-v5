package fiveman.hotelservice.controller;


import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.request.ServiceCategoryRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.ServiceCategoryResponse;
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
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<List<ServiceCategoryResponse>> getServiceCategories(){
        return new ResponseEntity<>(serviceCategoryService.getServiceCategories(), HttpStatus.OK);
    }

    @GetMapping("/serviceCategory/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<ServiceCategoryResponse> getServiceCategory(@PathVariable("id") long id){
        return new ResponseEntity<>(serviceCategoryService.getServiceCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/serviceCategory")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> saveServiceCategory(@RequestBody @Valid ServiceCategoryRequest serviceCategoryRequest){
       ServiceCategory serviceCategory = modelMapper.map(serviceCategoryRequest, ServiceCategory.class);
        return new ResponseEntity<>(serviceCategoryService.saveServiceCategory(serviceCategory), HttpStatus.OK);
    }


    @PutMapping("/serviceCategory")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> updateServiceCategory(@RequestBody ServiceCategoryRequest serviceCategoryRequest){
        ServiceCategory serviceCategory = modelMapper.map(serviceCategoryRequest, ServiceCategory.class);
        return new ResponseEntity<CustomResponseObject>(serviceCategoryService.updateServiceCategory(serviceCategory), HttpStatus.OK);
    }

    @DeleteMapping("/serviceCategory/{id}")
    @PreAuthorize("isAnonymous() or isAuthenticated()")
    public ResponseEntity<CustomResponseObject> deleteServiceCategory(@PathVariable("id") long id){
        return new ResponseEntity<CustomResponseObject>(serviceCategoryService.deleteServiceCategory(id), HttpStatus.OK);
    }
}
