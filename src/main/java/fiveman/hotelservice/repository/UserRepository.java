package fiveman.hotelservice.repository;


import fiveman.hotelservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username) throws UsernameNotFoundException;
    boolean existsByUsername(String username);
}
