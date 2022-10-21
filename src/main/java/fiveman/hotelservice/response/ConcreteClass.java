package fiveman.hotelservice.response;


public class ConcreteClass extends UserResponse {

    public ConcreteClass(long id, String username, String password, String firstName, String middleName, String lastName, boolean gender, String phoneNumber, String dateOfBirth, String userRole, boolean isActive, long hotel_Id) {
        super(id, username, password, firstName, middleName, lastName, gender, phoneNumber, dateOfBirth, userRole, isActive, hotel_Id);
    }
}
