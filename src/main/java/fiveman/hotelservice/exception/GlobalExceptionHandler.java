package fiveman.hotelservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fiveman.hotelservice.response.CustomResponseObject;

//import org.springframework.security.access.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler({ AppException.class })
	public ResponseEntity<CustomResponseObject> AppException(AppException e) {
		return ResponseEntity.status(e.getCode())
				.body(new CustomResponseObject(String.valueOf(e.getCode()), e.getErr().getMessage()));
	}

//	@ExceptionHandler(AccessDeniedException.class)
//	public ResponseEntity<CustomResponseObject> AccessDeniedException(AccessDeniedException e) {
//		logger.info(e.getMessage());
//		return ResponseEntity.status(403).body(new CustomResponseObject(String.valueOf(403), "Access is denied"));
//	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomResponseObject> handleUnwantedException(Exception e) {
		logger.info(e.getMessage());
		return ResponseEntity.status(500).body(new CustomResponseObject(String.valueOf(500), "Internal Server Error"));
	}

	@ExceptionHandler(BindException.class)
//	@ResponseStatus(HttpStatus.BAD_REQUEST)  // Nếu validate fail thì trả về 400
	public ResponseEntity<CustomResponseObject> handleBindException(BindException e) {
		// Trả về message của lỗi đầu tiên
		String errorMessage = "Request không hợp lệ";
		if (e.getBindingResult().hasErrors())
			e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return ResponseEntity.status(400).body(new CustomResponseObject(HttpStatus.BAD_REQUEST.toString(), errorMessage));
	}
}
