package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Service;
import fiveman.hotelservice.response.CustomResponseObject;

public interface ServiceService {
    List<Service> getAllServices();
    CustomResponseObject saveServices(Service service);
    Service getServiceById(Long id);
    CustomResponseObject updateService(Service service);
    CustomResponseObject deleteService(Long id);
    
}
