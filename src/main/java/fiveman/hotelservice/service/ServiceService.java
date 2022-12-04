package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Service;
import fiveman.hotelservice.response.ServiceResponse;

public interface ServiceService {


    List<ServiceResponse> getAllServices();
    List<Service> getAllServicesTest();

    List<ServiceResponse> getAllServicesByServiceCategory(long id);
    List<Service> getAllServicesByServiceCategoryTest(long id);
    List<ServiceResponse> saveServices(Service service);
    ServiceResponse getServiceById(Long id);
    List<ServiceResponse> updateService(Service service);
    List<ServiceResponse> deleteService(Long id);
    
}
