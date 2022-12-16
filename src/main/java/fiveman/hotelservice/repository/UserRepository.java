package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username) throws UsernameNotFoundException;

    boolean existsByUsername(String username);

    User findTopByOrderByIdDesc();

    User getUserById(long user_id);

    List<User> getAllUserByHotel_Id(long hotel_id);
}
