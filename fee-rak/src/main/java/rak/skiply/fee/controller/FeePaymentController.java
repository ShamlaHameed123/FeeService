package rak.skiply.fee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import rak.skiply.fee.dto.FeePaymentRequestDto;
import rak.skiply.fee.dto.FeePaymentResponseDto;
import rak.skiply.fee.dto.ReceiptRequestDto;
import rak.skiply.fee.service.FeePaymentService;
import rak.skiply.fee.utility.Constants;


@Slf4j
@RestController
@RequestMapping(value = Constants.FEE_SERVICE_BASE_URL)
public class FeePaymentController {
	
	@Autowired
    private FeePaymentService feePaymentService;
	
	
	@GetMapping(Constants.FEE_FETCH_DETAILS_URL)
    public ResponseEntity<FeePaymentResponseDto> getRecentPaymentDetailsForStudent(@Valid @RequestBody 
    															ReceiptRequestDto receiptRequestDto) {
        FeePaymentResponseDto recentPaymentDetails = feePaymentService.getRecentFeePaymentDetails(
        																receiptRequestDto.getStudentId());
        return ResponseEntity.status(HttpStatus.OK).body(recentPaymentDetails);
    }

    @PostMapping(Constants.FEE_SERVICE_EXECUTE_PAYMENT_URL)
    public ResponseEntity<FeePaymentResponseDto> executePayment(@Valid @RequestBody 
    														FeePaymentRequestDto feePaymentRequest){
    	return ResponseEntity.status(HttpStatus.OK).body(feePaymentService.executeFeePayment(
    																					feePaymentRequest));
        
    }
}
