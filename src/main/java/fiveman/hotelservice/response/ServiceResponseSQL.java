package fiveman.hotelservice.response;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

// @NamedNativeQuery(
//     name = "find_exist_taxi_service",
//     query =
//         "SELECT s.id, s.name, s.price, s.description, s.status, s.major_group, s.create_date, s.update_date, s.create_by, s.last_modify_by, s.service_category_id from order_detail od inner join service s on od.service_id = s.id inner join orders o on od.order_id = o.id where s.service_category_id = 4 and o.booking_id = :booking_id",
//     resultSetMapping = "service_response_sql"
// )
// @SqlResultSetMapping(
//     name = "service_response_sql",
//     classes = @ConstructorResult(
//         targetClass = ServiceResponseSQL.class,
//         columns = {
//             @ColumnResult(name = "id", type = Long.class),
//             @ColumnResult(name = "name", type = String.class),
//             @ColumnResult(name = "price", type = Double.class),
//             @ColumnResult(name = "description", type = String.class),
//             @ColumnResult(name = "major_group", type = String.class),
//             @ColumnResult(name = "create_date", type = String.class),
//             @ColumnResult(name = "update_date", type = String.class),
//         }
//     )
// )
public class ServiceResponseSQL {

    private long id;
    private String name;
    private double price;
    private String description;
    private boolean status;
    private String major_group;
    private String create_date;
    private String update_date;
    private String create_by;
    private String last_modify_by;
    private long service_category_id;


}
