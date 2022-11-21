package fiveman.hotelservice.service.Impl;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import fiveman.hotelservice.entities.*;
import fiveman.hotelservice.repository.*;
import fiveman.hotelservice.request.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import fiveman.hotelservice.exception.AppException;
import fiveman.hotelservice.response.OrderDetailResponse;
import fiveman.hotelservice.response.CustomResponseObject;
import fiveman.hotelservice.response.MomoResponse;
import fiveman.hotelservice.response.VnPayRes;
import fiveman.hotelservice.service.OrderDetailService;
import fiveman.hotelservice.service.PaymentService;
import fiveman.hotelservice.utils.Common;
import fiveman.hotelservice.utils.Utilities;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private OrderDetailService billDetailService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RequestServiceRepository requestServiceRepository;
    
    @Autowired
    private SpecialRequestRepository specialRequestRepository;

    @Autowired
    private HotelRepository hotelRepository;

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
//            CustomerInfoMomoRequest customerInfo = new CustomerInfoMomoRequest("dat", "0123456789", "dat@gmail.com");

        // long amount = 200000;
        byte[] array = new byte[10]; // length is bounded by 7
        new Random().nextBytes(array);
        String requestId = new String(array, Charset.forName("UTF-8"));

        String sign = "accessKey=" + Common.ACCESS_KEY + "&amount=" + request.getAmount() + "&extraData="
                + "&ipnUrl=" + Common.IPN_URL_MOMO + "&orderId=" + request.getOrderId() + "&orderInfo="
                + request.getOrderInfo()
                + "&partnerCode=" + Common.PARTNER_CODE + "&redirectUrl=" + Common.REDIRECT_URL_MOMO
                + "&requestId=" + requestId + "&requestType=captureWallet";

        String signatureHmac = "";
        try {
            signatureHmac = Utilities.calculateHMac(sign, Common.HMACSHA256, Common.SECRET_KEY);
            System.out.println("signature: " + signatureHmac + "   ");
        } catch (Exception e) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(),
                    new CustomResponseObject(Common.ADDING_FAIL, "pay fail"));
        }

        momoReq.setPartnerCode(Common.PARTNER_CODE);
        momoReq.setExtraData("");
        momoReq.setSignature(signatureHmac);
        momoReq.setAmount(request.getAmount());
        momoReq.setExtraData("");
        momoReq.setIpnUrl(Common.IPN_URL_MOMO);
        momoReq.setLang("vi");
        momoReq.setOrderId(request.getOrderId());
        momoReq.setOrderInfo(request.getOrderInfo());
        momoReq.setRedirectUrl(Common.REDIRECT_URL_MOMO);
        momoReq.setRequestId(request.getOrderId());
        momoReq.setRequestType("captureWallet");
        momoReq.setStoreId("1");

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
    public CustomResponseObject validateVNPay(VnPayConfirmRequest request) {
        Booking lastBooking = bookingRepository.findTopByOrderByIdDesc();
        int confirmation_No = lastBooking.getConfirmationNo() + 1;
        customerRepository.save(request.getCustomer());
        for (int i = 0; i < request.getRoomTypes().size(); i++) {
            Booking booking = new Booking();
            Hotel hotel = hotelRepository.getHotelById(request.getHotel_id());
            booking.setRoomTypeId(request.getRoomTypes().get(i).getId());
            booking.setCustomer(request.getCustomer());
            booking.setHotel(hotel);
            booking.setArrivalDate(request.getBookingDates().getStartDate());
            booking.setCreateBy(request.getCustomer().getFirstName() + " " + request.getCustomer().getMiddleName() + " " + request.getCustomer().getLastName());
            booking.setCreateDate(Utilities.getCurrentDate());
            booking.setConfirmationNo(confirmation_No);
            booking.setStatus(Common.BOOKING_BOOKED);
            booking.setRequestServices(mapRequestBookingToRequestService(request));
            booking.setDepartureDate(request.getBookingDates().getEndDate());
            if(!Utilities.isEmptyString(request.getSpecialUtilities().getDescription())){
                booking.setSpecialNote(request.getSpecialUtilities().getDescription());
            }
            if(!Utilities.isEmptyString(request.getVnp_Amount())){
                booking.setRoomPayment(String.valueOf(Double.parseDouble(request.getVnp_Amount())/100));
            }else{
                booking.setRoomPayment("N/A");
            }
            for (int j = 0; j < request.getPersons().size(); j++) {
                if(i == j){
                    booking.setNumOfAdult(request.getPersons().get(j).getAdult());
                    booking.setNumOfChildren(request.getPersons().get(j).getChild());
                }
            }
            bookingRepository.save(booking);

            if(request.getUtilities().size() > 0){
                for (SpecialUtility utilities : request.getUtilities()) {
                    SpecialRequest specialRequest = new SpecialRequest();
                    specialRequest.setBooking(booking);
                    specialRequest.setSpecialUtility(utilities);
                    specialRequestRepository.save(specialRequest);
                }
            }
        }

        return new CustomResponseObject(Common.ADDING_SUCCESS, "Booking Successfully");
    }

    public List<RequestService> mapRequestBookingToRequestService(VnPayConfirmRequest request){
        List<RequestService> list = new ArrayList<>();
        RequestService requestService = requestServiceRepository.getRequestServiceById(request.getRequestServiceBooking().getId());
        list.add(requestService);
        return list;
    }


    public void payLibs(List<OrderDetailResponse> list) {
        for (OrderDetailResponse billDetailResponse : list) {
            OrderDetail billDetail = mapper.map(billDetailResponse, OrderDetail.class);
            // billDetail.set(1);
            billDetailService.updateBillDetail(billDetail);
        }
    }

}
