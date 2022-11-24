package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.OrderDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    OrderDetail getOrderDetailById(long id);

    List<OrderDetail> getAllByOrder_Id(long order_id);

    OrderDetail findTopByOrderByIdDesc();


// //     select case when (count(scen) > 0)  then true else false end  
// // from Scenario scen where scen.name = :name
// @NamedNativeQuery(name = "ChessPlayer.findPlayerNameDtoById_Named",
//                   query = "SELECT p.first_name as first, p.last_name as last FROM Chess_Player p WHERE id = :id",
//                   resultSetMapping = "Mapping.PlayerNameDto")

//     @Query(value = "SELECT s.id, s.name, s.price, s.description, s.status, s.major_group,s.create_date, s.update_date, s.create_by, s.last_modify_by, s.service_category_id from order_detail od inner join service s on od.service_id = s.id inner join orders o on od.order_id = o.id where s.service_category_id = 4 and o.booking_id = :booking_id",
//      nativeQuery = true)
    //  @NamedNativeQuery(name = "",
    //                     query = "SELECT s.id, s.name, s.price, s.description, s.status, s.major_group,s.create_date, s.update_date, s.create_by, s.last_modify_by, s.service_category_id from order_detail od inner join service s on od.service_id = s.id inner join orders o on od.order_id = o.id where s.service_category_id = 4 and o.booking_id = :booking_id",
    //                     resultSetMapping = "Mapping.ServiceResponseSQL")
    // @SqlResultSetMapping(name = "Mapping.ServiceResponseSQL",me = "Mapping.ServiceResponseSQL",
    //                     classes = @ConstructorResult(targetClass = ServiceResponseSQL.class,
    //                                                 columns = {@ColumnResult(name = "id")}))
    // @Query(name = "SELECT s.id, s.name, s.price, s.description, s.status, s.major_group, s.create_date, s.update_date, s.create_by, s.last_modify_by, s.service_category_id from order_detail od inner join service s on od.service_id = s.id inner join orders o on od.order_id = o.id where s.service_category_id = 4 and o.booking_id = :booking_id", nativeQuery = true)
    //  ServiceResponseSQL existTaxiServiceInBooking(@Param("booking_id") long booking_id);
    }


