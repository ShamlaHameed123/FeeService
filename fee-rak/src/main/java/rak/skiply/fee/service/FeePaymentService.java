package rak.skiply.fee.service;

import rak.skiply.fee.dto.FeePaymentRequestDto;
import rak.skiply.fee.dto.FeePaymentResponseDto;

public interface FeePaymentService {
	
	public FeePaymentResponseDto executeFeePayment(FeePaymentRequestDto feePaymentRequestDto);
	
	public FeePaymentResponseDto viewReceipt(String referenceNumber);
	
	public FeePaymentResponseDto getRecentFeePaymentDetails(String studentId);

}
