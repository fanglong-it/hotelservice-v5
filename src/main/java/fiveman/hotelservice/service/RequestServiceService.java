package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.RequestService;
import fiveman.hotelservice.response.RequestServiceResponse;

public interface RequestServiceService {

      List<RequestServiceResponse> getAllRequestService();

      List<RequestServiceResponse> getRequestServiceByBookingId(long id);

      RequestServiceResponse getRequestService(long id);

      RequestServiceResponse saveRequestService(RequestService requestService);

      RequestServiceResponse updateRequestService(RequestService requestService);

      RequestServiceResponse deleteRequestService(long id);

}
