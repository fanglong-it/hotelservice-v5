package fiveman.hotelservice.service.Impl;

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

@Service
public class EmailSenderService implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendMail(List<BookingResponse> list) {

        //get price
        String totalPrice = getTotalPrice(list);
        List<PriceObject> listPrice = getPrice(list);

        // set context
        Context context = new Context();
        context.setVariable("list", list);
        context.setVariable("totalPrice", totalPrice);
        context.setVariable("listPrice", listPrice);

        String process = templateEngine.process("email", context);
        javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setSubject("Five Men Hotel - Booking Information");
            helper.setText(process, true);
            helper.setTo(list.get(0).getBooking().getCustomer().getEmail());
        } catch (MessagingException e) {
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, new CustomResponseObject("500", e.getMessage()));
        }
        javaMailSender.send(mimeMessage);
    }

    public String getTotalPrice(List<BookingResponse> list){
        double totalPrice = 0;
        double price = 0;
        double priceByRoom = 0;
        String currentDate = Utilities.getCurrentDate().split(" ")[0];
        for (BookingResponse bookingResponse: list) {
            if(bookingResponse.getBooking() != null){
                for (RoomPrice roPrice : bookingResponse.getRoomType().getRoomPrices()) {
                    if(roPrice.getDate().equals(currentDate)){
                        price = roPrice.getPrice();
                    }else {
                        price = 0;
                    }
                }
                if(price == 0){
                    price = bookingResponse.getRoomType().getDefaultPrice();
                }
                if(bookingResponse.getService() != null){
                    priceByRoom = (price + bookingResponse.getService().getPrice());
                    totalPrice += priceByRoom;
                }else{
                    totalPrice += price;
                }
            }
        }
        String priceInVND = Utilities.parseDoubleToVND(totalPrice);
        return priceInVND;
    }

    public List<PriceObject> getPrice(List<BookingResponse> list){
        List<PriceObject> priceObjectList = new ArrayList<>();

        String currentDate = Utilities.getCurrentDate().split(" ")[0];
        for (BookingResponse bookingResponse: list) {
           if(bookingResponse.getBooking() != null){
               PriceObject priceObject = new PriceObject();
               double price = 0;
               double priceByRoom = 0;
               
               //Get RoomPrice With Booking
               for (RoomPrice roPrice : bookingResponse.getRoomType().getRoomPrices()) {
                   if(roPrice.getDate().equals(currentDate)){
                       price = roPrice.getPrice();
                   }
               }
               if(price == 0){
                   price = bookingResponse.getRoomType().getDefaultPrice();
               }
               if(bookingResponse.getService() != null){
                   priceByRoom = (price + bookingResponse.getService().getPrice());
               }else{
                   priceByRoom = price;
               }
               priceObject.setPrice(Utilities.parseDoubleToVND(price));
               priceObject.setPriceByRoom(Utilities.parseDoubleToVND((priceByRoom)));
               priceObjectList.add(priceObject);
           }
        }
        return priceObjectList;
    }

    @Data
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class PriceObject{
        private String price;
        private String priceByRoom;
    }

}
