package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.ServiceCategoryRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.ServiceCategoryService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    @Override
    public ServiceCategory getServiceCategoryById(Long id) {
      log.info("START GETTING SERVICE_CATEGORY");
        return serviceCategoryRepository.getServiceCategoryById(id);
    }

    @Override
    public CustomResponseObject saveServiceCategory(ServiceCategory serviceCategory) {
        if(!serviceCategoryRepository.existsById(serviceCategory.getId())){
            log.info("START SAVING SERVICE_CATEGORY");
            serviceCategoryRepository.save(serviceCategory);
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Create Success");
        }
        throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                new CustomResponseObject(HttpStatus.ALREADY_REPORTED.toString(),
                        "Exist id = " + serviceCategory.getId()));
    }


    @Override
    public List<ServiceCategory> getServiceCategories() {
        log.info("START GET ALL SERVICE_CATEGORY");
        return serviceCategoryRepository.findAll();
    }
}
