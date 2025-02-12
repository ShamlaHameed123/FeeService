package rak.skiply.fee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import rak.skiply.fee.dto.FeePaymentResponseDto;
import rak.skiply.fee.dto.ReceiptRequestDto;
import rak.skiply.fee.service.FeePaymentService;
import rak.skiply.fee.utility.Constants;


@Slf4j
@Controller
@RequestMapping(value = Constants.RECEIPT_VIEW_BASE_URL)
public class ReceiptController {
	
	@Autowired
    private FeePaymentService feePaymentService;


	@GetMapping("/")
	public String home() {
		return "home"; // This will render home.html
	}

	@PostMapping(value = Constants.FEE_SERVICE_VIEW_RECEIPT, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String viewReceipt(@Valid ReceiptRequestDto receiptRequestDto, Model model) {
		FeePaymentResponseDto paymentDetails = feePaymentService.viewReceipt(receiptRequestDto.getStudentId());
		model.addAttribute("transaction", paymentDetails);
		return "viewReceipt";
	}


}
