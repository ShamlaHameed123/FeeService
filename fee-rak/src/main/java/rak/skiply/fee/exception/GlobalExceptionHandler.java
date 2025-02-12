package rak.skiply.fee.exception;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;
import rak.skiply.fee.utility.Constants;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = PaymentDoesNotExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleException(PaymentDoesNotExist ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    
	}
	
	@ExceptionHandler(value = PaymentAlreadyExist.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleStudentAlreadyExistsException(PaymentAlreadyExist ex) {
	    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
	}
	
	@ExceptionHandler(value = RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleOtherExceptions(RuntimeException ex) {
	    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}
	
	@ExceptionHandler(value = ClientServiceFailure.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleFeignClientExceptions(ClientServiceFailure ex) { 
		int exceptionMessagePosition = ex.getMessage().indexOf(Constants.FEIGN_ERROR_PREFIX_PATTERN);
		if(exceptionMessagePosition == -1) {
			return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), Constants.SERVICE_DOWN);
		}
		String exceptionMessage = ex.getMessage().substring(exceptionMessagePosition);
		JSONArray jsonArray = new JSONArray(exceptionMessage);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
	    return new ErrorResponse(jsonObject.getInt(Constants.ERROR_RESPONSE_STATUSCODE_KEY),
	    		jsonObject.get(Constants.ERROR_RESPONSE_MESSAGE_KEY).toString());
	}
	
	
}
