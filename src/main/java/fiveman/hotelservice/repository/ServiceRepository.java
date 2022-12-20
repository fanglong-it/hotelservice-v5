package fiveman.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fiveman.hotelservice.entities.Service;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service getServiceById(Long id);

    Service findTopByOrderByIdDesc();
    
    List<Service> getAllByServiceCategory_Id(long service_category_id);

    @Query(value = "select service.id from service inner join order_detail on service.id = order_detail.service_id where service.id != 70 and service.id != 71 and service.id != 57 and service.id != 58 group by service.id order by sum(order_detail.quantity) desc limit 3;", nativeQuery = true)
    List<String> getTop3ItemsBooked();
}
