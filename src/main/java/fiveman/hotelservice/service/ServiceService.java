package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Service;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.ServiceResponse;

public interface ServiceService {
    List<ServiceResponse> getAllServices();

    List<ServiceResponse> getAllServicesByServiceCategory(long id);

    CustomResponseObject saveServices(Service service);
    ServiceResponse getServiceById(Long id);
    CustomResponseObject updateService(Service service);
    CustomResponseObject deleteService(Long id);
    
}
