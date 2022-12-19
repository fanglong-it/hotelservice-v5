package fiveman.hotelservice.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.RequestService;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.BookingRepository;
import fiveman.hotelservice.repository.RequestServiceRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.RequestServiceResponse;
import fiveman.hotelservice.service.RequestServiceService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestServiceServiceImpl implements RequestServiceService {

      @Autowired
      private RequestServiceRepository requestServiceRepository;

      @Autowired
      ModelMapper modelMapper;

      RequestServiceResponse mapRequestServiceToResponse(RequestService requestService) {
            RequestServiceResponse requestServiceResponse = modelMapper.map(requestService,
                        RequestServiceResponse.class);
            requestServiceResponse.getBooking().setRequestServices(null);
            return requestServiceResponse;
      }

      @Override
      public List<RequestService> getAllRequestService() {
            log.info("GET ALL REQUEST SERVICES");
            List<RequestService> requestServices = requestServiceRepository.getAllRequestService();
            // List<RequestServiceResponse> requestServiceResponses = new ArrayList<>();
            // for (RequestService requestService : requestServices) {
            // RequestServiceResponse requestServiceResponse =
            // mapRequestServiceToResponse(requestService);
            // requestServiceResponses.add(requestServiceResponse);
            // }

            for (RequestService requestService : requestServices) {
                  requestService.getBooking().setRequestServices(null);
            }
            return requestServices;
      }

      @Override
      public RequestService getRequestService(long id) {
            log.info("START GET REQUEST SERVICE BY ID");
            if (!requestServiceRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
            }
            log.info("END GET REQUEST SERVICE BY ID");
            RequestService requestService = requestServiceRepository.getRequestServiceById(id);
            requestService.getBooking().setRequestServices(null);
            return requestService;
      }

      @Autowired
      BookingRepository bookingRepository;

      @Override
      public RequestService saveRequestService(RequestService requestService) {
            log.info("START SAVE REQUEST SERVICE");
            Booking booking = bookingRepository.getBookingById(requestService.getBooking().getId());
            boolean isTurnDownDone = false;
            boolean isCheckOutDone = false;
            List<RequestService> requestServices = booking.getRequestServices();
            for (RequestService rService : requestServices) {
                  if (rService.getRequestServiceType().equals(Common.REQUESTSERVICE_TYPE_TURNDOWN)) {
                        if (rService.getStatus().equals(Common.REQUESTSERVICE_DONE)) {
                              isTurnDownDone = true;
                        } else {
                              isTurnDownDone = false;
                        }
                  }
                  if (rService.getRequestServiceType().equals(Common.REQUESTSERVICE_TYPE_CHECKOUT)) {
                        if (rService.getStatus().equals(Common.REQUESTSERVICE_DONE)) {
                              isCheckOutDone = true;
                        } else {
                              isCheckOutDone = false;
                        }
                  }
            }
            // if (!isCheckOutDone) {
            // throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
            // new CustomResponseObject(Common.ADDING_FAIL,
            // "You can't request if there is Already Request Service"));
            // }

            if (requestService.getRequestServiceType().equals(Common.REQUESTSERVICE_TYPE_CHECKOUT)) {
                  if (isCheckOutDone) {
                        requestServiceRepository.save(requestService);
                  } else {
                        throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                                    new CustomResponseObject(Common.ADDING_FAIL,
                                                "You Can't send the CHECK OUT Request if already exist!"));
                  }
            }
            if (requestService.getRequestServiceType().equals(Common.REQUESTSERVICE_TYPE_TURNDOWN)) {
                  if (isTurnDownDone) {
                        requestServiceRepository.save(requestService);
                  } else {
                        throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                                    new CustomResponseObject(Common.ADDING_FAIL,
                                                "You Can't send the TURN DOWN Request if already exist!"));
                  }
            }
            requestService = requestServiceRepository.findTopByOrderByIdDesc();
            return requestService;
            // return mapRequestServiceToResponse(requestService);
      }

      @Override
      public RequestService updateRequestService(RequestService requestService) {
            log.info("START UPDATE REQUEST SERVICE");
            if (requestServiceRepository.existsById(requestService.getId())) {
                  requestServiceRepository.save(requestService);
                  log.info("END UPDATE REQUEST SERVICE");
                  // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
                  return requestServiceRepository.getRequestServiceById(requestService.getId());

            }
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + requestService.getId()));
      }

      @Override
      public RequestService deleteRequestService(long id) {
            if (requestServiceRepository.existsById(id)) {
                  log.info("DELETE REQUEST SERVICE");
                  RequestService requestService = requestServiceRepository.getRequestServiceById(id);
                  requestService.setStatus(Common.REQUESTSERVICE_DONE);
                  requestServiceRepository.save(requestService);
                  // requestServiceRepository.deleteById(id);
                  // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
                  return requestService;
            }
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
      }

      @Override
      public List<RequestService> getRequestServiceByBookingId(long id) {
            List<RequestService> requestServices = requestServiceRepository.getAllRequestServiceByBooking_Id(id);
            // List<RequestServiceResponse> requestServiceResponses = new ArrayList<>();
            for (RequestService requestService : requestServices) {
                  // RequestServiceResponse requestServiceResponse =
                  // mapRequestServiceToResponse(requestService);
                  // requestServiceResponses.add(requestServiceResponse);
                  requestService.getBooking().setRequestServices(null);
            }
            return requestServices;
      }

}
