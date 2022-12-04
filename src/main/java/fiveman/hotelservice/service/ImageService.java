package fiveman.hotelservice.service;

import fiveman.hotelservice.entities.Image;
import fiveman.hotelservice.response.CustomResponseObject;

import java.util.List;

public interface ImageService {

    List<Image> getAllImage();

    List<Image> getImageByImageType(String type);

    
    List<Image> getImageByImageTypeContain(String type);

    CustomResponseObject saveImage(Image image);

    Image getImageById(Long id);

    CustomResponseObject updateImage(Image image);

    CustomResponseObject deleteImage(Long id);
}
