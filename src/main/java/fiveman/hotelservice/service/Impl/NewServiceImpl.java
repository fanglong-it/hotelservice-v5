package fiveman.hotelservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import fiveman.hotelservice.entities.New;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.NewRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.NewService;
import fiveman.hotelservice.utils.Common;

@Service
public class NewServiceImpl implements NewService {

    @Autowired
    NewRepository newRepository;

    @Override
    public New getNewById(long id) {
        if(!newRepository.existsById(id)){
            throw new AppException(HttpStatus.NOT_FOUND, new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return newRepository.getNewById(id);
    }

    
    @Override
    public List<New> getNewByType(String type) {
        return newRepository.getAllByNewType(type);
    }


    @Override
    public List<New> getAllNew() {
        return newRepository.findAll();
    }

    @Override
    public CustomResponseObject saveNew(New oNew) {
        if(newRepository.existsById(oNew.getId())){
            throw new AppException(HttpStatus.ALREADY_REPORTED, new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + oNew.getId()));
        }
        newRepository.save(oNew);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updateNew(New oNew) {
        
        if(!newRepository.existsById(oNew.getId())){
            throw new AppException(HttpStatus.NOT_FOUND, new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + oNew.getId()));
        }
        newRepository.save(oNew);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }
    

    @Override
    public CustomResponseObject deleteNew(long id) {
        if(!newRepository.existsById(id)){
            throw new AppException(HttpStatus.NOT_FOUND, new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        newRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
    }


    
}
