package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.ServiceCategoryResponse;

import java.util.List;

public interface ServiceCategoryService {

    List<ServiceCategoryResponse> getServiceCategories();

    ServiceCategoryResponse getServiceCategoryById(Long id);

    CustomResponseObject saveServiceCategory(ServiceCategory serviceCategory);

    CustomResponseObject updateServiceCategory(ServiceCategory serviceCategory);

    CustomResponseObject deleteServiceCategory(long id);

}
