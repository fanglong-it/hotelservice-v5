package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Picture;
import fiveman.hotelservice.entities.Picture;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface PictureService {

    List<Picture> getAllPicture();
    List<Picture> getPictureByPictureType(String type);
    CustomResponseObject savePicture(Picture picture);
    Picture getPictureById(Long id);
    CustomResponseObject updatePicture(Picture picture);
    CustomResponseObject deletePicture(Long id);
}
