package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Picture;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.PictureRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.PictureService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureRepository pictureRepository;

    @Override
    public List<Picture> getAllPicture() {
        log.info("START OF GET ALL PICTURE");
        return pictureRepository.findAll();
    }

    @Override
    public List<Picture> getPictureByPictureType(String type) {
        log.info("START OF GET PICTURE BY PICTURE TYPE");
        return pictureRepository.findAllByPictureType(type);
    }

    @Override
    public CustomResponseObject savePicture(Picture picture) {
        if(!pictureRepository.existsById(picture.getId())){
            pictureRepository.save(picture);
            log.info("CREATE SUCCESS PICTURE");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Create Success");
        }
        throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(HttpStatus.ALREADY_REPORTED.toString(), "Existed id = " + picture.getId()));
    }

    @Override
    public Picture getPictureById(Long id) {
        log.info("START GETTING PICTURE");
        return pictureRepository.getPictureById(id);
    }

    @Override
    public CustomResponseObject updatePicture(Picture picture) {
        pictureRepository.save(picture);
        log.info("UPDATED PICTURE");
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success");
    }

    @Override
    public CustomResponseObject deletePicture(Long id) {

        if(pictureRepository.existsById(id)){
            log.info("EXIST ID START DELETE PICTURE");
            pictureRepository.deleteById(id);
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success");
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found id = " + id));
    }
}
