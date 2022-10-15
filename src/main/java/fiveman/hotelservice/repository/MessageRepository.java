package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{
      Message getMessageById(long id);
}
