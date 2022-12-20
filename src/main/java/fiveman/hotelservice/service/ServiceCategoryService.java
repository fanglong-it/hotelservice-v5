package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.response.ServiceCategoryResponse;

import java.util.List;

public interface ServiceCategoryService {

    List<ServiceCategory> getServiceCategories();

    List<ServiceCategoryResponse> getServiceCategoriesWithImage();

    ServiceCategoryResponse getServiceCategoryById(Long id);

    ServiceCategory saveServiceCategory(ServiceCategory serviceCategory);

    ServiceCategory updateServiceCategory(ServiceCategory serviceCategory);

    ServiceCategory deleteServiceCategory(long id);

}
