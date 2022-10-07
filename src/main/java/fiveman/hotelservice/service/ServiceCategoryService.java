package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface ServiceCategoryService {

    List<ServiceCategory> getServiceCategories();
    ServiceCategory getServiceCategoryById(Long id);
    CustomResponseObject saveServiceCategory(ServiceCategory serviceCategory);

}
