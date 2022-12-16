package fiveman.hotelservice.service.Impl;

import java.util.List;

import fiveman.hotelservice.request.NewRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.New;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.ImageRepository;
import fiveman.hotelservice.repository.NewRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.NewResponse;
import fiveman.hotelservice.service.NewService;
import fiveman.hotelservice.utils.Common;

@Service
public class NewServiceImpl implements NewService {

    @Autowired
    NewRepository newRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ImageRepository imageRepository;

    public NewResponse mapNewToResponse(New newObject) {
        NewResponse newResponse = modelMapper.map(newObject, NewResponse.class);
        newResponse.setHotel_Id(newObject.getHotel().getId());
        newResponse.setImages(imageRepository.getAllByPictureType("img_new_" + newResponse.getId()));
        return newResponse;
    }

    @Override
    public NewResponse getNewById(long id) {
        if (!newRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND,
                    new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return mapNewToResponse(newRepository.getNewById(id));
    }

    @Override
    public List<New> getNewByType(String type) {
        // List<NewResponse> newResponses = new ArrayList<>();
        List<New> news = newRepository.getAllBynewsType(type);
        // for (New new1 : news) {
        // NewResponse newResponse = mapNewToResponse(new1);
        // newResponses.add(newResponse);
        // }
        return news;
    }

    @Override
    public List<New> getAllNew() {
        // return newRepository.findAll();
        // List<NewResponse> newResponses = new ArrayList<>();
        List<New> news = newRepository.findAll();
        // for (New new1 : news) {
        // NewResponse newResponse = mapNewToResponse(new1);
        // newResponses.add(newResponse);
        // }
        return news;
    }

    @Override
    public New saveNew(New oNew) {
        if (newRepository.existsById(oNew.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED,
                    new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + oNew.getId()));
        }
        newRepository.save(oNew);
        // return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
        return newRepository.findTopByOrderByIdDesc();
    }

    @Override
    public New updateNew(New oNew) {

        if (!newRepository.existsById(oNew.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND,
                    new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + oNew.getId()));
        }
        newRepository.save(oNew);
        // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
        return newRepository.getNewById(oNew.getId());
    }

    @Override
    public New deleteNew(long id) {
        if (!newRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND,
                    new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        // newRepository.deleteById(id);
        New oNew = newRepository.getNewById(id);
        oNew.setStatus(Common.NEW_DELETE);
        newRepository.save(oNew);
        // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
        return newRepository.getNewById(id);
    }

    @Override
    public CustomResponseObject updateNewsEvent(List<NewRequest> newRequests) {
        for (NewRequest req : newRequests) {
            New news = modelMapper.map(req, New.class);
            if (!newRepository.existsById(news.getId())) {
                throw new AppException(HttpStatus.NOT_FOUND,
                        new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + news.getId()));
            }
            newRepository.save(news);
        }
        // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
        return new CustomResponseObject("200", "Adding success");
    }

}
