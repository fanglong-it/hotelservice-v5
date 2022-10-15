package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.Utilities;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.UtilitiesRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.UtilitiesService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtitliesServiceImpl implements UtilitiesService {

      @Autowired
      private UtilitiesRepository utilitiesRepository;

      @Override
      public List<Utilities> getAllUtilities() {
            log.info("START OF FIND ALL UTILITIES");
            return utilitiesRepository.findAll();
      }

      @Override
      public Utilities getUtilitiesById(long id) {
            log.info("START OF GET   ULTILITIES BY ID");
            if (utilitiesRepository.existsById(id)) {
                  return utilitiesRepository.getUtilitiesById(id);
            }
            log.info("END GET UTILITIES FAIL");
            throw new AppException(HttpStatus.NOT_FOUND.value(),
                        new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found Utilities Id = " + id));
      }

      @Override
      public CustomResponseObject saveUtilities(Utilities utilities) {
            log.info("START OF SAVE ULTILITIES BY ID");
            if (utilitiesRepository.existsById(utilities.getId())) {
                  throw new AppException(HttpStatus.ALREADY_REPORTED.value(),
                              new CustomResponseObject(Common.ADDING_FAIL, "Exist Id = " + utilities.getId()));
            }
            utilitiesRepository.save(utilities);
            log.info("END OF SAVE ULTILITIES BY ID");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding success!");
      }

      @Override
      public CustomResponseObject updateUtilities(Utilities utilities) {
            log.info("START OF UPDATE ULTILITIES BY ID");
            if (!utilitiesRepository.existsById(utilities.getId())) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.UPDATE_FAIL, "Not found Id = " + utilities.getId()));
            }
            utilitiesRepository.save(utilities);
            log.info("END OF UPDATE ULTILITIES BY ID");
            return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
      }

      @Override
      public CustomResponseObject deleteUtitlies(long id) {
            log.info("START OF DELETE UTILITIES");
            if (!utilitiesRepository.existsById(id)) {
                  throw new AppException(HttpStatus.NOT_FOUND.value(),
                              new CustomResponseObject(Common.DELETE_FAIL, "Not found Id = " + id));
            }
            utilitiesRepository.deleteById(id);
            log.info("END OF DELETE ULTILITIES BY ID");
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
      }

}
