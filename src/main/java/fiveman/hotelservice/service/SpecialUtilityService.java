package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.SpecialUtility;
import fiveman.hotelservice.response.CustomResponseObject;

public interface SpecialUtilityService {
      List<SpecialUtility> getAllSpecialUtility();
           
      SpecialUtility getSpecialUtility(long id);
      
      CustomResponseObject saveSpecialUtility(SpecialUtility specialUtility);

      CustomResponseObject updateSpecialUtility(SpecialUtility specialUtility);

      CustomResponseObject deleteSpecialUtility(long id);
      
}
