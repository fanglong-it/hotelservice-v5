package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Abstraction;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface AbstractionService {
    Abstraction getAbstractionById(long id);
    List<Abstraction> getAbstractions();
    List<Abstraction> getAbstractionsNotCustom();
    Abstraction saveAbstraction(Abstraction abstraction);
    Abstraction updateAbstraction(Abstraction abstraction);
    CustomResponseObject deleteAbstractionById(long id);
}
