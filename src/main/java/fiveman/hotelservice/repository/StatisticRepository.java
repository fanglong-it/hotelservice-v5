package fiveman.hotelservice.repository;

import fiveman.hotelservice.request.Statistic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StatisticRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> getRevenuesBetweenDates(String startDate, String endDate){
        Query query = entityManager.createQuery("select b.actualDepartureDate, sum(b.totalAmount) from Booking b where b.status = 'CHECK OUT' and b.actualDepartureDate between " + startDate + " and " + endDate + " group by b.actualDepartureDate ", Object[].class);
        List<Object[]> list = query.getResultList();
        return list;
    }

}
