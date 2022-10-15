package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.SpecialRequest;
import fiveman.hotelservice.response.CustomResponseObject;

public interface SpecialRequestService {
      List<SpecialRequest> getAllSpecialRequest();

      SpecialRequest getSpecialRequest(long id);

      CustomResponseObject saveSpecialRequest(SpecialRequest specialRequest);

      CustomResponseObject updateSpecialRequest(SpecialRequest specialRequest);

      CustomResponseObject deleteSpecialRequest(long id);
}
