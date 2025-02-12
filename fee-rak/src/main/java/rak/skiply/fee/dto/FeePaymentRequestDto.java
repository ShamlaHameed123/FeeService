package rak.skiply.fee.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FeePaymentRequestDto {
	
	@NotBlank(message = "Student id number should not be empty")
    private String studentId;
	
	@NotBlank(message = "Card type is mandatory")
    private String cardType;
	
	@NotBlank(message = "Card number should not be empty")
    private String cardNumber;

	@NotNull(message = "tuition fees should not be empty")
    private BigDecimal tuitionFees;
	
	@NotNull(message = "custom amount should not be empty")
    private BigDecimal customAmount;
	
	@NotBlank(message = "Currency should not be empty")
    private String currency;
	
	@NotNull(message = "noOfPayments")
	private int noOfPayments;

}
