package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.RequestService;
import fiveman.hotelservice.response.CustomResponseObject;

public interface RequestServiceService {
      List<RequestService> getAllRequestService();
      List<RequestService> getRequestServiceByBookingId(long id);
      
      RequestService getRequestService(long id);
      
      CustomResponseObject saveRequestService(RequestService requestService);

      CustomResponseObject updateRequestService(RequestService requestService);

      CustomResponseObject deleteRequestService(long id);
}
