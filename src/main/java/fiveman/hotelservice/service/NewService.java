package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.New;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.NewResponse;

public interface NewService {
    NewResponse getNewById(long id);
    List<NewResponse> getAllNew();
    List<NewResponse> getNewByType(String type);
    List<NewResponse> saveNew(New oNew);
    List<NewResponse> updateNew(New oNew);
    List<NewResponse> deleteNew(long id);
    
}
