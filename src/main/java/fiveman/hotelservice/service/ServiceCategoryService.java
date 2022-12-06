package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.response.ServiceCategoryResponse;

import java.util.List;

public interface ServiceCategoryService {
    List<ServiceCategory> getServiceCategories();
    ServiceCategoryResponse getServiceCategoryById(Long id);
    List<ServiceCategory> saveServiceCategory(ServiceCategory serviceCategory);
    List<ServiceCategory> updateServiceCategory(ServiceCategory serviceCategory);
    List<ServiceCategory> deleteServiceCategory(long id);

}
