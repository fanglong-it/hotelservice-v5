package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
      Image getPictureById(Long id);

      List<Image> getAllByPictureType(String type);
//    List<Image> getAllByPictureTypeContains(String type);
}
