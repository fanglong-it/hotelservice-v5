package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Service;

public interface ServiceService {
    List<Service> getAllServices();
    Service saveServices(Service service);
    Service getServiceById(Long id);
    Service updateService(Service service);
    String deleteService(Long id);
    
}
