package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Service;
import fiveman.hotelservice.response.ServiceResponse;

public interface ServiceService {

    List<Service> getAllServices();

    List<Service> getAllServicesByServiceCategory(long id);

    List<ServiceResponse> getAllServiceByCateWithImage(long id);

    Service saveServices(Service service);

    ServiceResponse getServiceById(Long id);

    Service updateService(Service service);

    Service deleteService(Long id);

    List<String> getTop3Services();

}
