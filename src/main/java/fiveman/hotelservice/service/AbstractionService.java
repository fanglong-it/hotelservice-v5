package fiveman.hotelservice.service;


import fiveman.hotelservice.entities.Abstraction;
import fiveman.hotelservice.response.AbstractionResponse;

import java.util.List;

public interface AbstractionService {
    Abstraction getAbstractionById(long id);
    List<Abstraction> getAbstractions();
    List<Abstraction> getAbstractionsNotCustom();
    List<Abstraction> saveAbstraction(Abstraction abstraction);
    List<Abstraction> updateAbstraction(Abstraction abstraction);
    List<Abstraction> deleteAbstractionById(long id);
}
