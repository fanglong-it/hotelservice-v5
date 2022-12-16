package fiveman.hotelservice.service;

import java.util.List;

import fiveman.hotelservice.entities.New;
import fiveman.hotelservice.request.NewRequest;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.NewResponse;

public interface NewService {
    NewResponse getNewById(long id);

    List<New> getAllNew();

    List<New> getNewByType(String type);

    New saveNew(New oNew);

    New updateNew(New oNew);

    New deleteNew(long id);

    CustomResponseObject updateNewsEvent(List<NewRequest> newRequests);
}
