package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Abstraction;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.AbstractionRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.AbstractionService;
import fiveman.hotelservice.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbstractionServiceImpl implements AbstractionService {

    @Autowired
    AbstractionRepository abstractionRepository;

    @Override
    public Abstraction getAbstractionById(long id) {
        if (!abstractionRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.GET_FAIL, "Not found id =" + id));
        }
        return abstractionRepository.getAbstractionById(id);
    }

    @Override
    public List<Abstraction> getAbstractions() {
        return abstractionRepository.findAll();
    }

    @Override
    public CustomResponseObject saveAbstraction(Abstraction abstraction) {
        if (abstractionRepository.existsById(abstraction.getId())) {
            throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(Common.ADDING_FAIL, "Exist id =" + abstraction.getId()));
        }
        abstractionRepository.save(abstraction);
        return new CustomResponseObject(Common.ADDING_SUCCESS, "Adding Success!");
    }

    @Override
    public CustomResponseObject updateAbstraction(Abstraction abstraction) {
        if (!abstractionRepository.existsById(abstraction.getId())) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.UPDATE_FAIL, "Not found id =" + abstraction.getId()));
        }
        abstractionRepository.save(abstraction);
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success!");
    }

    @Override
    public CustomResponseObject deleteAbstractionById(long id) {
        if (!abstractionRepository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(Common.DELETE_FAIL, "Not found id =" + id));
        }
        abstractionRepository.deleteById(id);
        return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success!");
    }
}
