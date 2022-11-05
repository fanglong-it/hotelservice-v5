package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.New;
import fiveman.hotelservice.response.CustomResponseObject;

public interface NewService {
    New getNewById(long id);
    java.util.List<New> getAllNew();
    CustomResponseObject saveNew(New oNew);
    CustomResponseObject updateNew(New oNew);
    CustomResponseObject deleteNew(long id);
    
}
