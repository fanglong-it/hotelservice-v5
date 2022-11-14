package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.OrderDetail;
import fiveman.hotelservice.entities.Service;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    OrderDetail getOrderDetailById(long id);

    List<OrderDetail> getAllByOrder_Id(long order_id);


//     select case when (count(scen) > 0)  then true else false end  
// from Scenario scen where scen.name = :name


    @Query(value = "SELECT CASE WHEN(count(*) > 0) then 'true' else 'false' end from order_detail od inner join service s on od.service_id = s.id inner join orders o on od.order_id = o.id where s.service_category_id = 4 and o.booking_id = :booking_id",
     nativeQuery = true)
    boolean existTaxiServiceInBooking(@Param("booking_id") long booking_id);

    }


