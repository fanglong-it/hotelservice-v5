package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.Utilities;
import fiveman.hotelservice.response.CustomResponseObject;

public interface UtilitiesService {
      List<Utilities> getAllUtilities();
      
      Utilities getUtilitiesById(long id);
      
      CustomResponseObject saveUtilities(Utilities utilities);
      
      CustomResponseObject updateUtilities(Utilities utilities);
      
      CustomResponseObject deleteUtitlies(long id);
}
