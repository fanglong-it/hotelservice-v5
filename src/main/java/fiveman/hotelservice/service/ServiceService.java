package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Service;
import fiveman.hotelservice.response.ServiceResponse;

public interface ServiceService {


    List<Service> getAllServices();
    List<Service> getAllServicesByServiceCategory(long id);
    List<ServiceResponse> getAllServiceByCateWithImage(long id);
    List<Service> saveServices(Service service);
    ServiceResponse getServiceById(Long id);
    List<Service> updateService(Service service);
    List<Service> deleteService(Long id);

    List<String> getTop3Services();
    
}
