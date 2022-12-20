package fiveman.hotelservice.service.Impl;

import fiveman.hotelservice.entities.Booking;
import fiveman.hotelservice.entities.RoomPrice;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.response.BookingResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.service.EmailService;
import fiveman.hotelservice.utils.Utilities;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class EmailSenderService implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendMail(List<BookingResponse> list) {

        // get price
        String totalPrice = getTotalPrice(list);
        List<PriceObject> listPrice = getPrice(list);

        // get email
        Email email = getEmail(list);

        // set context
        Context context = new Context();
        context.setVariable("list", list);
        context.setVariable("totalPrice", totalPrice);
        context.setVariable("listPrice", listPrice);
        context.setVariable("emailObj", email);

        String process = templateEngine.process("email", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject("Five Men Hotel - Booking Information");
            helper.setText(process, true);
            helper.setTo(email.getEmail());
        } catch (MessagingException e) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, new CustomResponseObject("500", e.getMessage()));
        }
        javaMailSender.send(mimeMessage);
    }

    public Email getEmail(List<BookingResponse> list) {
        String email = "";
        int firstIndex = -1;
        int lastIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getBooking() != null){
                email = list.get(i).getBooking().getCustomer().getEmail();
                if(firstIndex == -1){
                    firstIndex = i;
                }
                lastIndex = i;
            }
        }
        return new Email(email, firstIndex, lastIndex);
    }

    public String getTotalPrice(List<BookingResponse> list) {
        double totalPrice = 0;
        double priceByRoom = 0;
        // String currentDate = Utilities.getCurrentDate().split(" ")[0];
        for (BookingResponse bookingResponse : list) {
            if (bookingResponse.getBooking() != null) {
                double price = 0;

                Booking booking = bookingResponse.getBooking();

                // Get RoomPrice With Booking
                List<String> dates = Utilities.getStringDateBetweenArrivalAndDeparture(booking.getArrivalDate(),
                        booking.getDepartureDate());
                for (String date : dates) {
                    List<RoomPrice> rPrices = bookingResponse.getRoomType().getRoomPrices();
                    int result = Utilities.findIndex(rPrices, date);
                    if (result != -1) {
                        price += rPrices.get(result).getPrice();
                    } else {
                        price += bookingResponse.getRoomType().getDefaultPrice();
                    }
                }
                if (bookingResponse.getService() != null) {
                    priceByRoom = (price + bookingResponse.getService().getPrice());
                    totalPrice += priceByRoom;
                } else {
                    totalPrice += price;
                }
            }
        }
        String priceInVND = Utilities.parseDoubleToVND(totalPrice);
        return priceInVND;
    }

    public List<PriceObject> getPrice(List<BookingResponse> list) {
        List<PriceObject> priceObjectList = new ArrayList<>();

        // String currentDate = Utilities.getCurrentDate().split(" ")[0];
        for (BookingResponse bookingResponse : list) {
            PriceObject priceObject = new PriceObject();
            if (bookingResponse.getBooking() != null) {
                double price = 0;
                double priceByRoom = 0;

                Booking booking = bookingResponse.getBooking();

                // Get RoomPrice With Booking
                List<String> dates = Utilities.getStringDateBetweenArrivalAndDeparture(booking.getArrivalDate(),
                        booking.getDepartureDate());
                for (String date : dates) {

                    List<RoomPrice> rPrices = bookingResponse.getRoomType().getRoomPrices();
                    int result = Utilities.findIndex(rPrices, date);
                    if (result != -1) {
                        price += rPrices.get(result).getPrice();
                    } else {
                        price += bookingResponse.getRoomType().getDefaultPrice();
                    }
                }

                if (bookingResponse.getService() != null) {
                    priceByRoom = (price + bookingResponse.getService().getPrice());
                } else {
                    priceByRoom = price;
                }
                priceObject.setPrice(Utilities.parseDoubleToVND(price));
                priceObject.setPriceByRoom(Utilities.parseDoubleToVND((priceByRoom)));

            }
            priceObjectList.add(priceObject);
        }
        return priceObjectList;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class PriceObject {
        private String price;
        private String priceByRoom;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Email {
        private String email;
        private int firstIndex;
        private int lastIndex;
    }
}
