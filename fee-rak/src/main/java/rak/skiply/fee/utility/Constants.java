package rak.skiply.fee.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
	
	public static final String SOMETHING_WENT_WRONG = "Something went wrong, please try again later";
	public static final String SERVICE_DOWN = "Service is not responding now, please try again later";
	public static final String PAYMENT_NOT_FOUND =  "Payment does not exist for student";
	public static final String FEE_SERVICE_BASE_URL =  "/fee-payment";
	public static final String FEE_SERVICE_EXECUTE_PAYMENT_URL =  "/executePayment";
	public static final String FEE_SERVICE_VIEW_RECEIPT =  "/view";
	public static final String UNEXPECTED_ERROR_MSG = "Unexpected error occurred: {}";
	public static final String PAYMENT_ALREADY_EXIST = "Payment already completed for this student for the month";
	public static final String FEIGN_ERROR_PREFIX_PATTERN = "[{\"statusCode\"";
	public static final String ERROR_RESPONSE_MESSAGE_KEY = "message";
	public static final String ERROR_RESPONSE_STATUSCODE_KEY = "statusCode";
	public static final String FEE_FETCH_DETAILS_URL = "/details";
	public static final String RECEIPT_VIEW_BASE_URL = "/receipt";


}
