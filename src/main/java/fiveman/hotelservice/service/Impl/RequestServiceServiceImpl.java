package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.RequestService;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.RequestServiceRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.RequestServiceService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestServiceServiceImpl implements RequestServiceService{
      
      @Autowired
      private RequestServiceRepository requestServiceRepository;
      
      @Override
      public List<RequestService> getAllRequestService() {
            log.info("GET ALL REQUEST SERVICES");
            return requestServiceRepository.findAll();
      }

      @Override
      public RequestService getRequestService(long id) {
            log.info("START GET REQUEST SERVICE BY ID");
            if (!requestServiceRepository.existsById(id)) {
                throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Cant found ID =" + id));
            }
            log.info("END GET REQUEST SERVICE BY ID");
            return requestServiceRepository.getRequestServiceById(id);
      }

      @Override
      public List<RequestService> saveRequestService(RequestService requestService) {
            log.info("START SAVE REQUEST SERVICE");
            if (requestServiceRepository.existsById(requestService.getId())) {
                throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + requestService.getId()));
            }
            requestServiceRepository.save(requestService);
            log.info("END SAVE REQUEST SERVICE");
            // return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding REQUEST SERVICE Success!");
            return getAllRequestService();

      }

      @Override
      public List<RequestService> updateRequestService(RequestService requestService) {
            log.info("START UPDATE REQUEST SERVICE");
            if (requestServiceRepository.existsById(requestService.getId())) {
                requestServiceRepository.save(requestService);
                log.info("END UPDATE REQUEST SERVICE");
            //     return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update success!");
                return getAllRequestService();

            }
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id = " + requestService.getId()));
      }

      @Override
      public List<RequestService> deleteRequestService(long id) {
            if (requestServiceRepository.existsById(id)) {
                  log.info("DELETE REQUEST SERVICE");
                  requestServiceRepository.deleteById(id);
                  // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete success!");
                  return getAllRequestService();
              }
              throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id = " + id));
      }

      @Override
      public List<RequestService> getRequestServiceByBookingId(long id) {
            return requestServiceRepository.getAllRequestServiceByBooking_Id(id);
      }

      

}
