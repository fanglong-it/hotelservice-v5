package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.SpecialRequest;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.SpecialRequestRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.SpecialRequestService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SpecialRequestServiceImpl implements SpecialRequestService {

      @Autowired
      private SpecialRequestRepository specialRequestRepository;

      @Override
      public List<SpecialRequest> getAllSpecialRequest() {
            log.info("START OF FIND ALL SPECIAL REQUEST");
            return specialRequestRepository.findAll();
      }

      @Override
      public SpecialRequest getSpecialRequest(long id) {
            log.info("START OF GET   ULTILITIES BY ID");
            if (specialRequestRepository.existsById(id)) {
                  return specialRequestRepository.getSpecialRequestById(id);
            }
            log.info("END GET SPECIAL REQUEST FAIL");
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(
                        HttpStatus.NOT_FOUND.toString(), "Not found SPECIAL REQUEST Id = " + id));
      }

      @Override
      public CustomResponseObject saveSpecialRequest(SpecialRequest specialRequest) {
            log.info("START OF SAVE ULTILITIES BY ID");
            if (specialRequestRepository.existsById(specialRequest.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist Id = " + specialRequest.getId()));
            }
            specialRequestRepository.save(specialRequest);
            log.info("END OF SAVE ULTILITIES BY ID");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding success!");
      }

      @Override
      public CustomResponseObject updateSpecialRequest(SpecialRequest specialRequest) {
            log.info("START OF UPDATE ULTILITIES BY ID");
            if (!specialRequestRepository.existsById(specialRequest.getId())) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.UPDATE_FAIL, "Not found Id = " + specialRequest.getId()));
            }
            specialRequestRepository.save(specialRequest);
            log.info("END OF UPDATE ULTILITIES BY ID");
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
      }

      @Override
      public CustomResponseObject deleteSpecialRequest(long id) {
            log.info("START OF DELETE SPECIAL REQUEST");
            if (!specialRequestRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.DELETE_FAIL, "Not found Id = " + id));
            }
            specialRequestRepository.deleteById(id);
            log.info("END OF DELETE ULTILITIES BY ID");
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
      }

}
