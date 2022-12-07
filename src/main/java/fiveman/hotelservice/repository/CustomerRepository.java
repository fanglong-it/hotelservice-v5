package fiveman.hotelservice.repository;

import fiveman.hotelservice.entities.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer getCustomerById(long id);
    Customer findTopByOrderByIdDesc();


    @Query(value = "select cus.id, cus.birth_date, cus.create_by, cus.create_date, cus.email, cus.first_name, cus.gender, cus.id_no, cus.last_modify_by, cus.last_name, cus.middle_name, cus.passport_no, cus.phone_number, cus.update_date from customer_stay_booking stb inner join booking b on stb.booking_id = b.id Inner join customer cus on stb.customer_id = cus.id where b.id = :booking_id and b.status = 'CHECK IN'", nativeQuery = true)
    List<Customer> getCustomerStayBooking(long booking_id);

    @Query(value = "select c.id, c.birth_date, create_by, c.create_date, c.email, c.first_name, c.gender, c.id_no, c.last_modify_by, c.last_name, c.middle_name, c.passport_no, c.phone_number, c.update_date from customer_stay_booking csb inner join customer c on csb.customer_id = c.id where csb.booking_id = :booking_id and csb.primary_customer is not null", nativeQuery = true)
    Customer getPrimaryCustomer(long booking_id);

    @Query(value = "select c.id, c.birth_date, create_by, c.create_date, c.email, c.first_name, c.gender, c.id_no, c.last_modify_by, c.last_name, c.middle_name, c.passport_no, c.phone_number, c.update_date from customer_stay_booking csb inner join customer c on csb.customer_id = c.id where csb.primary_customer is not null", nativeQuery = true)
    List<Customer> getAllPrimaryCustomer();
}

