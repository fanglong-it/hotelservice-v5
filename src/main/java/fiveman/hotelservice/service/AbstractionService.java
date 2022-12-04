package fiveman.hotelservice.service;


import fiveman.hotelservice.entities.Abstraction;
import fiveman.hotelservice.response.AbstractionResponse;

import java.util.List;

public interface AbstractionService {
    Abstraction getAbstractionById(long id);

    List<AbstractionResponse> getAbstractions();
    List<Abstraction> getAbstractionsNotCustom();
    // List<Abstraction> getAbstractions();

    List<AbstractionResponse> saveAbstraction(Abstraction abstraction);

    List<AbstractionResponse> updateAbstraction(Abstraction abstraction);

    List<AbstractionResponse> deleteAbstractionById(long id);
}
