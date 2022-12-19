package fiveman.hotelservice.service.Impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;
import fiveman.hotelservice.entities.*;
import fiveman.hotelservice.repository.*;
import fiveman.hotelservice.request.*;
import fiveman.hotelservice.response.*;
import fiveman.hotelservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.service.PaymentService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;

@Service
public class PaymentServiceImpl implements PaymentService {

    // @Autowired
    // private OrderDetailService billDetailService;

    // @Autowired
    // private ModelMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // @Autowired
    // private RequestServiceRepository requestServiceRepository;

    @Autowired
    private SpecialRequestRepository specialRequestRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private OrderPaymentRepository orderPaymentRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoomPriceRepository roomPriceRepository;

    @Override
    public ResponseEntity<MomoResponse> getPaymentMomo(MomoClientRequest request) {

        // request url
        String url = Common.MOMO_URI;

        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // create a post object

        // build the request
        MomoRequest momoReq = new MomoRequest();
        // CustomerInfoMomoRequest customerInfo = new CustomerInfoMomoRequest("dat",
        // "0123456789", "dat@gmail.com");
        List<String> oList = request.getOrderId();
        String orderId = String.join("-", oList);
        // String requestId = 
        // Order order = orderRepository.getOrderById(Long.parseLong(request.getOrderId()));

        // long amount = 200000;
        // byte[] array = new byte[10]; // length is bounded by 7
        // new Random().nextBytes(array);


        // String requestId = new String(array, Charset.forName("UTF-8"));
        // String requestId = String.valueOf(order.getId());

        DecimalFormat df = new DecimalFormat("#");

        String amount = String.valueOf(df.format(request.getAmount()));

        String sign = "accessKey=" + Common.ACCESS_KEY + "&amount=" + amount + "&extraData="
                + "&ipnUrl=" + Common.IPN_URL_MOMO + "&orderId=" + orderId + "&orderInfo="
                + "Thanh toan momo"
                + "&partnerCode=" + Common.PARTNER_CODE + "&redirectUrl=" + Common.REDIRECT_URL_MOMO
                + "&requestId=" + orderId + "&requestType=captureWallet";

        // accessKey=$accessKey&amount=$amount&extraData=$extraData
        // &ipnUrl=$ipnUrl&orderId=$orderId&orderInfo=$orderInfo
        // &partnerCode=$partnerCode&redirectUrl=$redirectUrl
        // &requestId=$requestId&requestType=$requestType

        String signatureHmac = "";
        try {
            signatureHmac = Utilities.calculateHMac(sign, Common.HMACSHA256, Common.SECRET_KEY);
            System.out.println("signature: " + signatureHmac + "   ");
        } catch (Exception e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.ADDING_FAIL, "Signature bị lỗi"));
        }

        momoReq.setPartnerCode(Common.PARTNER_CODE);
        momoReq.setSignature(signatureHmac);
        momoReq.setAmount(Long.valueOf(amount));
        momoReq.setExtraData("");
        momoReq.setIpnUrl(Common.IPN_URL_MOMO);
        momoReq.setLang("vi");
        momoReq.setOrderId(orderId);
        momoReq.setOrderInfo("Thanh toan momo");
        momoReq.setRedirectUrl(Common.REDIRECT_URL_MOMO);
        momoReq.setRequestId(orderId);
        momoReq.setRequestType("captureWallet");

        HttpEntity<MomoRequest> req = new HttpEntity<>(momoReq, headers);

        // send POST request
        try {
            ResponseEntity<MomoResponse> response = restTemplate.postForEntity(url, req, MomoResponse.class);

            if (response != null) {
                return response;
            }
        } catch (Exception e) {
            String arr[] = String.valueOf(e.getMessage()).split(",");
            String ar[] = arr[1].split(":");
            String message = ar[1].replaceAll("\"", "");
            System.out.println("" + e.getMessage());
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.ADDING_FAIL, message));
        }
        return null;

    }

    @Override
    public VnPayRes getPaymentVNPay(VNPayRequest request) {
        // get system param
        String vnp_Version = Common.VNP_VERSION;
        String vnp_Command = Common.VNP_COMMAND;
        String vnp_TmnCode = Common.VNP_TMNCODE;

        // get request param
        long amount = request.getVnp_amount() * 100;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        String bank_code = request.getVnp_BankCode();
        if (bank_code != null && !bank_code.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bank_code);
        }

        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String vnp_TxnRef = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", request.getVnp_OrderInfo());
        vnp_Params.put("vnp_OrderType", Common.VNP_ORDER_TYPE_HOTEL);

        String locale = request.getVnp_Locale();
        if (locale != null && !locale.isEmpty()) {
            vnp_Params.put("vnp_Locale", locale);
        } else {
            vnp_Params.put("vnp_Locale", "vi");
        }

        vnp_Params.put("vnp_ReturnUrl", Common.VNP_RETURNURL);
        vnp_Params.put("vnp_IpAddr", request.getVnp_IpAddr());

        String vnp_CreateDate = Utilities.getCurrentDateByFormat("yyyyMMddHHmmss");

        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        String vnp_ExpireDate = Utilities.getExpireDate("yyyyMMddHHmmss");
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        // get query String with param in hash map
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();

        try {
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    // Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    // Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = Utilities.hmacSHA512(Common.VNP_HASHSCRET, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = Common.VNP_URI + "?" + queryUrl;

        VnPayRes res = new VnPayRes();
        res.setCode("00");
        res.setMessage("sucess");
        res.setUrl(paymentUrl);

        return res;
    }

    @Override
    public List<BookingResponse> validateVNPay(VnPayConfirmRequest request) {
        Booking lastBooking = bookingRepository.findTopByOrderByIdDesc();
        int confirmation_No = 0;
        if(lastBooking != null){
            confirmation_No = lastBooking.getConfirmationNo() + 1;
        }else{
            confirmation_No = 1;
        }
        Hotel hotel = hotelRepository.getHotelById(request.getHotel_id());
        List<BookingResponse> listBooking = new ArrayList<>();

        // List<BookingResponse.BookingFailureRoom> listRoomBusy = new ArrayList<>();
        for (int i = 0; i < request.getRoomTypes().size(); i++) {
            RoomType roomType = roomTypeRepository.getRoomTypeById(request.getRoomTypes().get(i).getId());
            BookingResponse bookingResponse = new BookingResponse();
            List<Order> orderList = new ArrayList<>();
            if (roomType.getMaxBookingRoom() != 0) {
                if (i == 0) {
                    customerRepository.save(request.getCustomer());
                }
                bookingResponse = new BookingResponse();
                List<OrderDetail> orderDetailList = new ArrayList<>();

                Order order = new Order();
                OrderDetail orderDetail = new OrderDetail();

                // set order and order detail if exist
                if (request.getServiceBooking().getId() > 0) {
                    fiveman.hotelservice.entities.Service service = serviceRepository.getServiceById(request.getServiceBooking().getId());
                    //set service into response
                    bookingResponse.setService(service);
                    // set Order for booking
                    order.setCreateDate(Utilities.getCurrentDateByFormat("dd/MM/yyyy HH:mm:ss"));
                    order.setCreateBy(request.getCustomer().getFirstName() + " " + request.getCustomer().getMiddleName() + " " + request.getCustomer().getLastName());
                    if (request.getPaymentMethod() != 0) {
                        order.setStatus(Common.BOOKING_DONE);
                        // set payment method
                        PaymentMethod paymentMethod = paymentMethodRepository.getPaymentMethodById(request.getPaymentMethod());

                        //set order payment method
                        OrderPayment orderPayment = new OrderPayment();
                        orderPayment.setPaymentAmount(service.getPrice());
                        orderPayment.setPaymentMethod(paymentMethod);
                        orderPayment.setDateTime(Utilities.getCurrentDateByFormat("dd/MM/yyyy HH:mm:ss"));
                        orderPaymentRepository.save(orderPayment);

                        // set order payment to order
                        OrderPayment orPay = orderPaymentRepository.findTopByOrderByIdDesc();
                        order.setOrderPayment(orPay);
                    } else {
                        order.setStatus(Common.BOOKING_BOOKED);
                    }

                    // order detail

                    orderDetail.setService(service);
                    orderDetail.setQuantity(1);
                    orderDetail.setPrice(service.getPrice());
                    orderDetail.setAmount(orderDetail.getQuantity() * orderDetail.getPrice());
                    orderDetail.setOrderDate(Utilities.getCurrentDateByFormat("dd/MM/yyyy HH:ss:mm"));

                    orderDetailRepository.save(orderDetail);
                    OrderDetail orDetail = orderDetailRepository.findTopByOrderByIdDesc();
                    orderDetailList.add(orDetail);

                    order.setTotalAmount(orderDetail.getAmount());
                    order.setOrderDetails(orderDetailList);
                    orderRepository.save(order);
                    Order or = orderRepository.findTopByOrderByIdDesc();
                    orderList.add(or);
                }

                // set booking
                roomType.setMaxBookingRoom(roomType.getMaxBookingRoom() - 1);
                // set RoomPrice
                List<RoomPrice> listRoomPrice = roomType.getRoomPrices();
                for (RoomPrice roomPrice : listRoomPrice) {
                    boolean isPriceByDate = fiveman.hotelservice.utils.Utilities.compareTwoDateString(request.getBookingDates().getStartDate(), roomPrice.getDate());
                    if (isPriceByDate) {
                        if (roomType.getMaxBookingRoom() > roomPrice.getMaxBookingRoom()) {
                            roomPrice.setMaxBookingRoom(roomPrice.getMaxBookingRoom() - 1);
                            roomType.setMaxBookingRoom(roomPrice.getMaxBookingRoom() - 1);
                            roomPriceRepository.save(roomPrice);
                        }
                    }
                }

//            RoomTypeRequest roomTypeRequest = mapper.map(roomType, RoomTypeRequest.class);
                Booking booking = new Booking();
                booking.setRoomTypeId(request.getRoomTypes().get(i).getId());
                booking.setCustomer(request.getCustomer());
                booking.setHotel(hotel);
                booking.setArrivalDate(request.getBookingDates().getStartDate() + " " + hotel.getCheckInTime());
                booking.setCreateBy(request.getCustomer().getFirstName() + " " + request.getCustomer().getMiddleName() + " "
                        + request.getCustomer().getLastName());
                booking.setCreateDate(Utilities.getCurrentDateByFormat("dd/MM/yyyy HH:mm:ss"));
                booking.setConfirmationNo(confirmation_No);
                booking.setStatus(Common.BOOKING_BOOKED);
                booking.setDepartureDate(request.getBookingDates().getEndDate() + " " + hotel.getCheckOutTime());

                // set booking notes
                if (!Utilities.isEmptyString(request.getBookingNotes())) {
                    booking.setSpecialNote(request.getBookingNotes());
                }

                // set booking room payment
                if (request.getPaymentMethod() != 0) {
                    booking.setRoomPayment(String.valueOf(request.getRoomTypes().get(i).getPrice()));
                } else {
                    booking.setRoomPayment("N/A");
                }

                // set child and adult
                for (int j = 0; j < request.getPersons().size(); j++) {
                    if (i == j) {
                        booking.setNumOfAdult(request.getPersons().get(j).getAdult());
                        booking.setNumOfChildren(request.getPersons().get(j).getChild());
                    }
                }

                //set list order
                booking.setOrders(orderList);
                bookingRepository.save(booking);

                if (request.getSpecialUtilities().size() > 0) {
                    for (SpecialUtility specialUtility : request.getSpecialUtilities()) {
                        SpecialRequest specialRequest = new SpecialRequest();
                        specialRequest.setBooking(booking);
                        specialRequest.setSpecialUtility(specialUtility);
                        specialRequestRepository.save(specialRequest);
                    }
                }

                Booking bookingLatest = bookingRepository.findTopByOrderByIdDesc();
                //set booking order
                for (Order orderSave : orderList) {
                    orderSave.setBooking(bookingLatest);
                    orderRepository.save(orderSave);
                }

                //set orderDetail
                for (OrderDetail orDetail : orderDetailList) {
                    orDetail.setOrder(orderList.get(0));
                    orderDetailRepository.save(orDetail);
                }
                roomTypeRepository.save(roomType);
                bookingResponse.setBooking(bookingLatest);
                bookingResponse.setRoomType(roomType);
                bookingResponse.setHotel(hotel);

            } else {
                BookingResponse.BookingFailureRoom bookingFailureRoom = new BookingResponse.BookingFailureRoom();
                bookingFailureRoom.setStartDate(request.getBookingDates().getStartDate());
                bookingFailureRoom.setEndDate(request.getBookingDates().getEndDate());
                bookingFailureRoom.setAdult(request.getPersons().get(i).getAdult());
                bookingFailureRoom.setChild(request.getPersons().get(i).getChild());
                bookingFailureRoom.setBookingFailureRoomName(roomType.getName());
                bookingResponse.setBookingFailureRoom(bookingFailureRoom);
            }
            listBooking.add(bookingResponse);
        }
        boolean isPaymentSuccess = false;
        for (BookingResponse res: listBooking
             ) {
            if(res.getBooking() != null){
                isPaymentSuccess = true;
            }
        }
        if (isPaymentSuccess) {
            emailService.sendMail(listBooking);
        }
        return listBooking;
    }
}
