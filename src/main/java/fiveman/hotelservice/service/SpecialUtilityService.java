package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.SpecialUtility;

public interface SpecialUtilityService {
      List<SpecialUtility> getAllSpecialUtility();
           
      SpecialUtility getSpecialUtility(long id);
      
      SpecialUtility saveSpecialUtility(SpecialUtility specialUtility);

      SpecialUtility updateSpecialUtility(SpecialUtility specialUtility);

      SpecialUtility deleteSpecialUtility(long id);
      
}
