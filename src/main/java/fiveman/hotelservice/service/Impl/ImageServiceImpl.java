package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Image;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.repository.ImageRepository;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.ImageService;
import fiveman.hotelservice.utils.Common;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<Image> getAllImage() {
        log.info("START OF GET ALL Image");
        return imageRepository.findAll();
    }

    @Override
    public List<Image> getImageByImageType(String type) {
        log.info("START OF GET PICTURE BY IMAGE TYPE");
        return imageRepository.getAllByPictureType(type);
    }

    @Override
    public CustomResponseObject saveImage(Image image) {
        if (!imageRepository.existsById(image.getId())) {
            imageRepository.save(image);
            log.info("CREATE SUCCESS IMAGE");
            return new CustomResponseObject(Common.ADDING_SUCCESS, "Create Success");
        }
        throw new AppException(HttpStatus.ALREADY_REPORTED.value(), new CustomResponseObject(HttpStatus.ALREADY_REPORTED.toString(), "Existed id = " + image.getId()));
    }

    @Override
    public Image getImageById(Long id) {
        log.info("START GETTING PICTURE");
        return imageRepository.getPictureById(id);
    }

    @Override
    public CustomResponseObject updateImage(Image image) {
        imageRepository.save(image);
        log.info("UPDATED IMAGE");
        return new CustomResponseObject(Common.UPDATE_SUCCESS, "Update Success");
    }

    @Override
    public CustomResponseObject deleteImage(Long id) {

        if (imageRepository.existsById(id)) {
            log.info("EXIST ID START DELETE IMAGE");
            imageRepository.deleteById(id);
            return new CustomResponseObject(Common.DELETE_SUCCESS, "Delete Success");
        }
        throw new AppException(HttpStatus.NOT_FOUND.value(), new CustomResponseObject(HttpStatus.NOT_FOUND.toString(), "Not found id = " + id));
    }
}
