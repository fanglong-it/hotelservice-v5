package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.ServiceCategory;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.ImageRepository;
import fiveman.hotelservice.repository.ServiceCategoryRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.ServiceCategoryResponse;
import fiveman.hotelservice.service.ServiceCategoryService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ServiceCategoryServiceImpl implements ServiceCategoryService {

    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    private ModelMapper modelMapper;

    ServiceCategoryResponse mapServiceCategoryToResponse(ServiceCategory serviceCategory) {
        ServiceCategoryResponse serviceCategoryResponse = new ServiceCategoryResponse();
        serviceCategoryResponse.setId(serviceCategory.getId());
        serviceCategoryResponse.setName(serviceCategory.getName());
        serviceCategoryResponse.setDescription(serviceCategory.getDescription());
        serviceCategoryResponse.setFoodAndBeverage(serviceCategory.isFoodAndBeverage());
        serviceCategoryResponse.setOrdered(serviceCategory.isOrdered());
        serviceCategoryResponse.setStatus(serviceCategory.isStatus());
        serviceCategoryResponse
                .setImages(imageRepository.getAllByPictureType("img_serviceCategory_" + serviceCategory.getId()));
        // serviceCategoryResponse.setHotel_Id(serviceCategory.getHotel().getId());
        return serviceCategoryResponse;
    }

    @Override
    public ServiceCategory updateServiceCategory(ServiceCategory serviceCategory) {
        if (!serviceCategoryRepository.existsById(serviceCategory.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND, new CustomResponseObject(Common.UPDATE_FAIL, "Update fail!"));
        }
        serviceCategoryRepository.save(serviceCategory);
        return serviceCategoryRepository.getServiceCategoryById(serviceCategory.getId());
    }

    @Override
    public ServiceCategory deleteServiceCategory(long id) {
        if (!serviceCategoryRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, new CustomResponseObject(Common.DELETE_FAIL, "delete fail!"));
        }
        // serviceCategoryRepository.deleteById(id);
        ServiceCategory serviceCategory = serviceCategoryRepository.getServiceCategoryById(id);
        serviceCategory.setStatus(false);
        serviceCategoryRepository.save(serviceCategory);
        // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
        return serviceCategoryRepository.getServiceCategoryById(id);
    }

    @Override
    public ServiceCategoryResponse getServiceCategoryById(Long id) {
        log.info("START GETTING SERVICE_CATEGORY");
        return mapServiceCategoryToResponse(serviceCategoryRepository.getServiceCategoryById(id));
    }

    @Override
    public ServiceCategory saveServiceCategory(ServiceCategory serviceCategory) {
        if (!serviceCategoryRepository.existsById(serviceCategory.getId())) {
            log.info("START SAVING SERVICE_CATEGORY");
            serviceCategoryRepository.save(serviceCategory);
            // return new CustomResponseObject(Common.ADDING_SUCCESS, "Create Success");
            return serviceCategoryRepository.findTopByOrderByIdDesc();
        }
        throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                new CustomResponseObject(HttpStatus.ALREADY_REPORTED.toString(),
                        "Exist id = " + serviceCategory.getId()));
    }

    @Override
    public List<ServiceCategory> getServiceCategories() {
        log.info("START GET ALL SERVICE_CATEGORY");
        List<ServiceCategory> serviceCategories = serviceCategoryRepository.findAll();
        return serviceCategories;
    }

    @Override
    public List<ServiceCategoryResponse> getServiceCategoriesWithImage() {
        log.info("START GET ALL SERVICE_CATEGORY");
        List<ServiceCategory> serviceCategories = serviceCategoryRepository.findAll();
        List<ServiceCategoryResponse> serviceCategoryResponses = new ArrayList<>();
        for (ServiceCategory serviceCategory : serviceCategories) {
            ServiceCategoryResponse response = modelMapper.map(serviceCategory, ServiceCategoryResponse.class);
            response.setImages(imageRepository.getAllByPictureType("img_serviceCategory_" + serviceCategory.getId()));
            serviceCategoryResponses.add(response);
        }
        return serviceCategoryResponses;
    }

}
