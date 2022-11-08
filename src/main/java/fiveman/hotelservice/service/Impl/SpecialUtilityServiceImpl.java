package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.SpecialUtility;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.SpecialUtilityRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.SpecialUtilityService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SpecialUtilityServiceImpl implements SpecialUtilityService {

      @Autowired
      private SpecialUtilityRepository specialUtilityRepository;

      @Override
      public List<SpecialUtility> getAllSpecialUtility() {
            log.info("START OF FIND ALL SPECIAL UTILITY");
            return specialUtilityRepository.findAll();
      }

      @Override
      public SpecialUtility getSpecialUtility(long id) {
            log.info("START OF GET   ULTILITIES BY ID");
            if (specialUtilityRepository.existsById(id)) {
                  return specialUtilityRepository.getSpecialUtilityById(id);
            }
            log.info("END GET SPECIAL UTILITY FAIL");
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(
                        HttpStatus.NOT_FOUND.toString(), "Not found SPECIAL UTILITY Id = " + id));
      }

      @Override
      public CustomResponseObject saveSpecialUtility(SpecialUtility specialUtility) {
            log.info("START OF SAVE ULTILITIES BY ID");
            if (specialUtilityRepository.existsById(specialUtility.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist Id = " + specialUtility.getId()));
            }
            specialUtilityRepository.save(specialUtility);
            log.info("END OF SAVE ULTILITIES BY ID");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding success!");
      }

      @Override
      public CustomResponseObject updateSpecialUtility(SpecialUtility specialUtility) {
            log.info("START OF UPDATE ULTILITIES BY ID");
            if (!specialUtilityRepository.existsById(specialUtility.getId())) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.UPDATE_FAIL, "Not found Id = " + specialUtility.getId()));
            }
            specialUtilityRepository.save(specialUtility);
            log.info("END OF UPDATE ULTILITIES BY ID");
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
      }

      @Override
      public CustomResponseObject deleteSpecialUtility(long id) {
            log.info("START OF DELETE SPECIAL UTILITY");
            if (!specialUtilityRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.DELETE_FAIL, "Not found Id = " + id));
            }
            specialUtilityRepository.deleteById(id);
            log.info("END OF DELETE ULTILITIES BY ID");
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
      }

}
