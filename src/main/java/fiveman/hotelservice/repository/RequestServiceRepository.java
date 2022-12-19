package fiveman.hotelservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.RequestService;

@Repository
public interface RequestServiceRepository extends JpaRepository<RequestService, Long> {
      RequestService getRequestServiceById(long id);

      RequestService findTopByOrderByIdDesc();

      List<RequestService> getAllRequestServiceByBooking_Id(long id);

      @Query(value = "select rs.id, rs.date_time, rs.request_service_name, rs.request_service_type, rs.status, rs.booking_id from request_service rs", nativeQuery = true)
      List<RequestService> getAllRequestService();
}
