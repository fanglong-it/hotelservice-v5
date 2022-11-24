package fiveman.hotelservice.service;


import fiveman.hotelservice.entities.Abstraction;
import fiveman.hotelservice.response.AbstractionResponse;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface AbstractionService {
    AbstractionResponse getAbstractionById(long id);

    List<AbstractionResponse> getAbstractions();

    List<AbstractionResponse> saveAbstraction(Abstraction abstraction);

    List<AbstractionResponse> updateAbstraction(Abstraction abstraction);

    List<AbstractionResponse> deleteAbstractionById(long id);
}
