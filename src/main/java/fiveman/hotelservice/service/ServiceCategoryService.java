package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.response.ServiceCategoryResponse;

import java.util.List;

public interface ServiceCategoryService {

    List<ServiceCategoryResponse> getServiceCategories();

    ServiceCategoryResponse getServiceCategoryById(Long id);

    List<ServiceCategoryResponse> saveServiceCategory(ServiceCategory serviceCategory);

    List<ServiceCategoryResponse> updateServiceCategory(ServiceCategory serviceCategory);

    List<ServiceCategoryResponse> deleteServiceCategory(long id);

}
