package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.New;
import fiveman.hotelservice.response.CustomResponseObject;

public interface NewService {
    New getNewById(long id);
    java.util.List<New> getAllNew();
    List<New> getNewByType(String type);
    CustomResponseObject saveNew(New oNew);
    CustomResponseObject updateNew(New oNew);
    CustomResponseObject deleteNew(long id);
    
}
