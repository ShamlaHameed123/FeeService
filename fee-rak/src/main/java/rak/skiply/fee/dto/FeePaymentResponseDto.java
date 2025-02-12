package rak.skiply.fee.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FeePaymentResponseDto {
	
	
	private String referenceNumber;
	
	
    private String studentId;
	
	
    private String cardType;
	
    
    private String cardNumber;


    private BigDecimal tuitionFees;
	
	
    private BigDecimal customAmount;
	
	
    private String currency;
    
    @JsonFormat(pattern = "dd MMMM yyyy, HH:mm")
    private LocalDateTime paymentDateTime;
    
    private String studentName;
    
    private String schoolName;
    
    private int grade;
    
    private int noOfPayments;
	

}
