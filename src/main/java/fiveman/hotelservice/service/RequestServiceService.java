package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.RequestService;
import fiveman.hotelservice.response.RequestServiceResponse;

public interface RequestServiceService {

      List<RequestService> getAllRequestService();

      List<RequestServiceResponse> getRequestServiceByBookingId(long id);
      
      RequestServiceResponse getRequestService(long id);
      
      RequestService saveRequestService(RequestService requestService);

      RequestService updateRequestService(RequestService requestService);

      RequestService deleteRequestService(long id);

      
}
