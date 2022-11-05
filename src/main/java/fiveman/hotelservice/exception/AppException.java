package fiveman.hotelservice.exception;


import org.springframework.http.HttpStatus;

import fiveman.hotelservice.response.CustomResponseObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AppException extends RuntimeException{
    public AppException(HttpStatus notFound, CustomResponseObject err2) {
      }
/**
	 * 
	 */
	private static final long serialVersionUID = -4646555295720924268L;
	private int code;
    private CustomResponseObject err;
}
