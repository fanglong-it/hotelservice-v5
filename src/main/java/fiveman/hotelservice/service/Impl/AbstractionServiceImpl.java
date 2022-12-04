package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Abstraction;
import fiveman.hotelservice.entities.Image;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.AbstractionRepository;
import fiveman.hotelservice.repository.ImageRepository;
import fiveman.hotelservice.response.AbstractionResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.AbstractionService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AbstractionServiceImpl implements AbstractionService {

    @Autowired
    AbstractionRepository abstractionRepository;

    // private long id;

    // private String name;

    // private double longtitude;
    // private double latidute; 

    // private String openTime;
    // private String closeTime;
    // private String address;
    // private String description;

    // private Long hotel_Id;

    @Autowired
    ImageRepository imageRepository;
    public AbstractionResponse mapAbstractionToResponse(Abstraction abstraction){
        AbstractionResponse abstractionResponse = new AbstractionResponse();
        abstractionResponse.setId(abstraction.getId());
        abstractionResponse.setName(abstraction.getName());
        abstractionResponse.setLongtitude(abstraction.getLongtitude());
        abstractionResponse.setLatidute(abstraction.getLatidute());
        abstractionResponse.setOpenTime(abstraction.getOpenTime());
        abstractionResponse.setCloseTime(abstraction.getCloseTime());
        abstractionResponse.setAddress(abstraction.getAddress());
        abstractionResponse.setDescription(abstraction.getDescription());
        abstractionResponse.setHotel_Id(abstraction.getHotel().getId());
        abstractionResponse.setImages(imageRepository.getAllByPictureType("img_abstraction_"+ abstractionResponse.getId()));
        return abstractionResponse;
    }

    @Override
    public Abstraction getAbstractionById(long id) {
        if (!abstractionRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        // return mapAbstractionToResponse(abstractionRepository.getAbstractionById(id));
        return abstractionRepository.getAbstractionById(id);
    }

    // @Override
    // public List<AbstractionResponse> getAbstractions() {

    //     List<Abstraction> abstractions = abstractionRepository.findAll();
    //     List<AbstractionResponse> abstractionResponses = new ArrayList<>();

    //     for (Abstraction abstraction : abstractions) {
    //         abstractionResponses.add(mapAbstractionToResponse(abstraction));
    //     }

    //     return abstractionResponses;
    // }

    @Override
    public List<Abstraction> getAbstractions() {
        // TODO Auto-generated method stub
        return abstractionRepository.findAbstractions();
    }
    
    @Override
    public List<Abstraction> saveAbstraction(Abstraction abstraction) {
        if (abstractionRepository.existsById(abstraction.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + abstraction.getId()));
        }
        abstractionRepository.save(abstraction);
        // return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
        return getAbstractions();
    }

  

    @Override
    public List<Abstraction> updateAbstraction(Abstraction abstraction) {
        if (!abstractionRepository.existsById(abstraction.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + abstraction.getId()));
        }
        abstractionRepository.save(abstraction);
        // return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
        return getAbstractions();
    }

    @Override
    public List<Abstraction> deleteAbstractionById(long id) {
        if (!abstractionRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        abstractionRepository.deleteById(id);
        // return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
        return getAbstractions();
    }
}
