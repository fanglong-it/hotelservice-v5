package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.New;
import fiveman.hotelservice.response.NewResponse;

public interface NewService {
    NewResponse getNewById(long id);
    List<New> getAllNew();
    List<New> getNewByType(String type);
    List<New> saveNew(New oNew);
    List<New> updateNew(New oNew);
    List<New> deleteNew(long id);
    
}
