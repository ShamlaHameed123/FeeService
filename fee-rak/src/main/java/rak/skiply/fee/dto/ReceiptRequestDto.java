package rak.skiply.fee.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReceiptRequestDto {
	
	@NotBlank(message = "Student id number should not be empty")
    private String studentId;

}
