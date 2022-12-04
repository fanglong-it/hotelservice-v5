package fiveman.hotelservice.service.Impl;

import java.util.ArrayList;
import java.util.List;

import fiveman.hotelservice.utils.Utilities;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import fiveman.hotelservice.entities.Image;
import fiveman.hotelservice.entities.Service;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.ImageRepository;
import fiveman.hotelservice.repository.ServiceCategoryRepository;
import fiveman.hotelservice.repository.ServiceRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.ServiceResponse;
import fiveman.hotelservice.service.ServiceService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    
    // private long id;
    // private String name;
    // private double price;
    // private String description;
    // private boolean status;
    // private String majorGroup;

    // private String image;
    // private String createDate;
    // private String updateDate;
    // private String createBy;
    // private String lastModifyBy;
    // private ServiceCategory serviceCategory;

    @Autowired
    ImageRepository imageRepository;
    
    @Autowired 
    ModelMapper modelMapper;
    

    ServiceResponse mapServiceToResponse(Service service){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setId(service.getId());
        serviceResponse.setName(service.getName());
        serviceResponse.setPrice(service.getPrice());
        serviceResponse.setDescription(service.getDescription());
        serviceResponse.setMajorGroup(service.getMajorGroup());
        serviceResponse.setStatus(service.isStatus());
        serviceResponse.setImage(imageRepository.getAllByPictureType("img_service_"+service.getId()));
        serviceResponse.setCreateDate(service.getCreateDate());
        serviceResponse.setUpdateDate(service.getUpdateDate());
        serviceResponse.setCreateBy(service.getCreateBy());
        serviceResponse.setLastModifyBy(service.getLastModifyBy());
        service.getServiceCategory().setHotel(null);
        service.getServiceCategory().setServices(null);
        serviceResponse.setServiceCategory(service.getServiceCategory());
        return serviceResponse;
    }

    @Override
    public List<ServiceResponse> getAllServicesByServiceCategory(long id) {
        List<Service> services = serviceRepository.getAllByServiceCategory_Id(id);
        List<ServiceResponse> serviceResponses = new ArrayList<>();
        for (Service service : services) {
            ServiceResponse response = mapServiceToResponse(service);
            List<Image> images = imageRepository.getAllByPictureType("img_service_" + service.getId());
            response.setImage(images);
            serviceResponses.add(response);
        }
        return serviceResponses;
    }

    

    @Override
    public List<Service> getAllServicesByServiceCategoryTest(long id) {
        return serviceRepository.getAllByServiceCategory_Id(id);
    }



    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public List<ServiceResponse> getAllServices() {
        log.info("START OF GET ALL SERVICES!!!");
        List<Service> services = serviceRepository.findAll();
        List<ServiceResponse> serviceResponses = new ArrayList<>();
        for (Service service : services) {
            serviceResponses.add(mapServiceToResponse(service));
        }
        return serviceResponses;
    }

    

    @Override
    public List<Service> getAllServicesTest() {
        return serviceRepository.findAll();
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        log.info("START OF GET SERVICE BY ID");
        if (serviceRepository.existsById(id)) {
            return mapServiceToResponse(serviceRepository.getServiceById(id));
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(),
                new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found service by id = " + id));
    }


    @Override
    public List<ServiceResponse> updateService(fiveman.hotelservice.entities.Service service) {
        log.info("CHECKING ID FOR UPDATE SERVICE");
        if (serviceRepository.existsById(service.getId())) {
            log.info("ID IS EXIST START OF UPDATE SERVICE");
            service.setUpdateDate(Utilities.getCurrentDateByFormat("dd/MM/yyyy"));
            serviceRepository.save(service);
            serviceRepository.getServiceById(service.getId());

            //            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
            return getAllServices();
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(),
                new CustomResponseObject(Common.UPDATE_FAIL,
                "Not found service by id = " + service.getId()));
    }

    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;

    @Override
    public List<ServiceResponse> deleteService(Long id) {
        log.info("CHECKING ID FOR DELETE SERVICE");
        if (serviceRepository.existsById(id)) {
            log.info("START OF DELETE SERVICE BY ID");
//            serviceRepository.delete(serviceRepository.getServiceById(id));
            Service service  = serviceRepository.getServiceById(id);
            service.setStatus(false);
            service.setUpdateDate(Utilities.getCurrentDateByFormat("dd/MM/yyyy"));
            serviceRepository.save(service);
            //            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
            return getAllServices();
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(),
                new CustomResponseObject(Common.DELETE_FAIL, "Not found service by id = " + id));
    }

    @Override
    public List<ServiceResponse> saveServices(fiveman.hotelservice.entities.Service service) {
        if (!serviceRepository.existsById(service.getId())) {
            service.setServiceCategory(serviceCategoryRepository.getServiceCategoryById(service.getServiceCategory().getId()));
            service.setCreateDate(Utilities.getCurrentDateByFormat("dd/MM/yyyy"));
            serviceRepository.save(service);
            return getAllServices();
        }
        throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                new CustomResponseObject(Common.ADDING_FAIL, "Is exist service id =" + service.getId()));
    }

}
