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
    
}






















