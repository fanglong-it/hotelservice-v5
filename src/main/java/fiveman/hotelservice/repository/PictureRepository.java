package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    Picture getPictureById(Long id);
    List<Picture> findAllByPictureType(String type);
}
