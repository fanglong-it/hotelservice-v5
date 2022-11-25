package fiveman.hotelservice.utils;


import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class Common {
    public static final int PAGE_SIZE = 5; //default size of the product
    public static final int PAGE_INDEX = 0; //default page index



    // Default value for user
    public static final String USER_NAME = "USER";

    // Default value for overview service
    public static final String OVERVIEW_TITLE = "Hotel Service";
    public static final String OVERVIEW_IMAGE_URL = "Not Found Image";
    public static final String OVERVIEW_DESCRIPTION = "No Description";

    // Default value for RoomType
    public static final String ROOM_TYPE_NAME = "Room Type";
    public static final String ROOM_TYPE_IMAGE_URL = "Not found Image";
    public static final String ROOM_TYPE_DESCRIPTION = "No Description";


    // Default value for Services
    public static final String SERVICE_NAME = "Default name";
    public static final String SERVICE_PICTURE = "Not found Image";
    public static final String SERVICE_DESCRIPTION = "Default Description";
    public static final boolean SERVICE_ISEXTERNAL = false;

    //default Role
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";
    
    // Code for access is denied
    public static final String ACCESS_DENY = "HS-00000";
    
    // Code - Message
    public static final String SIGN_UP_SUCCESS = "HS-00001";
    public static final String SIGN_UP_FAIL = "HS-00002"; 
    
    // Code for adding - success and fail  announcement
    public static final String ADDING_SUCCESS = "HS-00010";
    public static final String ADDING_FAIL = "HS-00011";
    
    // Code for updating - success and fail  announcement
    public static final String UPDATE_SUCCESS = "HS-00012";
    public static final String UPDATE_FAIL = "HS-00013";
    
    // Code for delete - success and fail  announcement
    public static final String DELETE_SUCCESS = "HS-00014";
    public static final String DELETE_FAIL = "HS-00015";
    
    // Code for getting - success and fail  announcement
    public static final String GET_FAIL = "HS-00016";
    
    // althogrithm
    public static final String HMACSHA256 = "HmacSHA256";
    public static final String HMACSHA512 = "HmacSHA512";

    //Booking status
    public static  final String BOOKING_BOOKED = "booked";
    public static  final String BOOKING_NOT_SHOW = "not show";
    public static  final String BOOKING_DONE = "done";
    public static  final String BOOKING_CANCEL = "cancel";

    public static final String BOOKING_CHECKIN = "Check In";
    public static final String BOOKING_CHECKOUT = "Check Out";
    

    
    
    // code for momo payment
    public static final String IPN_URL_MOMO = "https://hotelservice-v5.herokuapp.com/api/v1/MomoConfirm";
    // public static final String IPN_URL_MOMO = "http://localhost:8080/api/v1/MomoConfirm";
    
    public static final String REDIRECT_URL_MOMO = "https://hotelservice-v5.herokuapp.com/api/v1/MomoConfirm";
    // public static final String REDIRECT_URL_MOMO = "http://localhost:8080/api/v1/MomoConfirm";
    
    public static final String PARTNER_CODE = "MOMODJMX20220717";
    public static final String ACCESS_KEY = "WehkypIRwPP14mHb";
    public static final String SECRET_KEY = "3fq8h4CqAAPZcTTb3nCDpFKwEkQDsZzz";
    public static final String MOMO_URI = "https://test-payment.momo.vn/v2/gateway/api/create";
    
    // code for VNPAY
    public static final String VNP_COMMAND = "pay";
    public static final String VNP_CURCODE = "VND";
    public static final String VNP_VERSION = "2.1.0";
    public static final String VNP_TMNCODE = "B3GJ4EAH";
    public static final String VNP_HASHSCRET = "RAOFLVMYIXMFIPSSRIFYIAWLBOSIJTPQ";
    public static final String VNP_URI = "http://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String VNP_ORDER_TYPE_HOTEL = "170000";
    public static final String VNP_RETURNURL = "http://localhost:3000/RoomValidate";
    public static final String VNP_RETURN_URL_APP = "";


    
}






















