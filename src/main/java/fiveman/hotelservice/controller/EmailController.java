package fiveman.hotelservice.controller;


import fiveman.hotelservice.entities.*;
import fiveman.hotelservice.response.BookingResponse;
import fiveman.hotelservice.service.EmailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "Email")
@RequestMapping("/api/v1/")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    @PreAuthorize("isAuthenticated() or isAnonymous()")
    public String sendEmail() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(78);
        orderDetail.setOrderDate("2022-11-24T16:14:35.134891700");
        orderDetail.setPrice(2500000);
        orderDetail.setQuantity(1);
        orderDetailList.add(orderDetail);

        List<Order> listOrder = new ArrayList<>();
        Order order = new Order();
        order.setId(81);
        order.setCreateBy("Phạm Thanh Đạt");
        order.setCreateDate("2022-11-24T16:14:34.799858800");
        order.setStatus("done");
        order.setTotalAmount(250000);
        order.setOrderDetails(orderDetailList);

        Customer customer = new Customer();
        customer.setEmail("datln0424@gmail.com");
        customer.setFirstName("Phạm");
        customer.setMiddleName("Thanh");
        customer.setLastName("Đạt");
        customer.setPassportNo(0);
        customer.setPhoneNumber("0987654321");


        Booking booking = new Booking();
        booking.setArrivalDate("24/11/2022 00:00:00");
        booking.setConfirmationNo(54);
        booking.setCreateBy("Phạm Thanh Đạt");
        booking.setCreateDate("2022-11-24T16:14:35.769737500");
        booking.setCustomer(customer);
        booking.setDepartureDate("25/11/2022 00:00:00");
        booking.setId(59);
        booking.setNumOfAdult(1);
        booking.setNumOfChildren(0);
        booking.setRoomPayment("N/A");
        booking.setRoomTypeId(1);
        booking.setTotalAmount(0);
        booking.setStatus("booked");
        booking.setOrders(listOrder);

        Hotel hotel = new Hotel();
        hotel.setFullName("Five Men Hotel");
        hotel.setId(1);
        hotel.setEmail("5MenHotel@gmail.com");
        hotel.setAddress("Lê Văn Việt - Quận 9 - Thành Phố Thủ Đức");
        hotel.setCheckInTime("14:00");
        hotel.setCheckOutTime("12:00");

        List<RoomPrice> roomPrices = new ArrayList<>();
        RoomPrice roomPrice1 = new RoomPrice();
        roomPrice1.setId(1);
        roomPrice1.setDate("11/11/2022");
        roomPrice1.setMaxBookingRoom(3);
        roomPrice1.setPrice(1500000);

        RoomPrice roomPrice12 = new RoomPrice();
        roomPrice12.setId(2);
        roomPrice12.setDate("13/11/2022");
        roomPrice12.setMaxBookingRoom(3);
        roomPrice12.setPrice(1800000);

        roomPrices.add(roomPrice1);
        roomPrices.add(roomPrice12);


        RoomType roomType = new RoomType();
        roomType.setId(1);
        roomType.setDefaultPrice(1800000);
        roomType.setName("Standard Room");
        roomType.setRoomPrices(roomPrices);

        Service service = new Service();
        service.setId(57);
        service.setDescription("1 chiều từ khách sạn đến sân bay");
        service.setPrice(250000);
        service.setName("Đưa đón sân bay (Tối đa 4 hành khách)");

        BookingResponse bookingResponse = new BookingResponse(booking,roomType,hotel,service);
        List<BookingResponse> list = new ArrayList<>();
        list.add(bookingResponse);
        list.add(bookingResponse);
        emailService.sendMail(list);
        return "Send Email Successfully";
    }
}
