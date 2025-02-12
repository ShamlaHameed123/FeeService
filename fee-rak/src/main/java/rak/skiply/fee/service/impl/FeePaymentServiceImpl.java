package rak.skiply.fee.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import rak.skiply.common_services.dto.StudentDto;
import rak.skiply.fee.client.StudentClient;
import rak.skiply.fee.dto.FeePaymentRequestDto;
import rak.skiply.fee.dto.FeePaymentResponseDto;
import rak.skiply.fee.entity.FeePayment;
import rak.skiply.fee.exception.ClientServiceFailure;
import rak.skiply.fee.exception.PaymentAlreadyExist;
import rak.skiply.fee.exception.PaymentDoesNotExist;
import rak.skiply.fee.repository.FeePaymentRepository;
import rak.skiply.fee.service.FeePaymentService;
import rak.skiply.fee.utility.Constants;
import rak.skiply.fee.utility.Utility;

@Slf4j
@Service
public class FeePaymentServiceImpl implements FeePaymentService{
	
	private static final Logger logger = LoggerFactory.getLogger(FeePaymentServiceImpl.class);
	
	@Autowired
	private FeePaymentRepository feePaymentRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private StudentClient studentClient;

	@Transactional
	public FeePaymentResponseDto executeFeePayment(FeePaymentRequestDto feePaymentRequestDto) {
		try {
		validateStudent(feePaymentRequestDto.getStudentId());
		validatePayment(feePaymentRequestDto.getStudentId(), true);
		BigDecimal totalTuitionFees = feePaymentRequestDto.getTuitionFees().multiply(
										BigDecimal.valueOf(feePaymentRequestDto.getNoOfPayments()));
		feePaymentRequestDto.setTuitionFees(totalTuitionFees);
		FeePayment newFeePayment = mapper.map(feePaymentRequestDto, FeePayment.class);
		newFeePayment.setReferenceNumber(String.valueOf(Utility.generateRandom(12)));
		feePaymentRepository.save(newFeePayment);
		return mapStudentAndPayment(newFeePayment);		
		}
		catch(ClientServiceFailure ex) {
			logger.error(Constants.SERVICE_DOWN, ex.getMessage(), ex);
			throw new ClientServiceFailure(ex.getMessage());
		}
		catch(PaymentAlreadyExist ex) {
			logger.error(Constants.PAYMENT_ALREADY_EXIST, ex.getMessage(), ex);
			throw new PaymentAlreadyExist(Constants.PAYMENT_ALREADY_EXIST);
		}
		catch(Exception ex) {
			logger.error(Constants.SOMETHING_WENT_WRONG, ex.getMessage(), ex);
			throw new RuntimeException(Constants.SOMETHING_WENT_WRONG);
		}
		
	}
	

	private StudentDto validateStudent(String studentId) {
		try {
		ResponseEntity<StudentDto> studentDetailsResponse = studentClient.getStudentDetails(studentId);
		return studentDetailsResponse.getBody();
		}
		catch(FeignException ex) {
			logger.error(Constants.SERVICE_DOWN, ex.getMessage(), ex);
			throw new ClientServiceFailure(ex.getMessage());
		}
	}


	private FeePaymentResponseDto mapStudentAndPayment(FeePayment newFeePayment) {
		FeePaymentResponseDto feePaidDetails = mapper.map(newFeePayment, FeePaymentResponseDto.class);
		StudentDto studentDetails = validateStudent(newFeePayment.getStudentId());
		feePaidDetails.setGrade(studentDetails.getGrade());
		feePaidDetails.setStudentName(studentDetails.getStudentName());
		feePaidDetails.setSchoolName(studentDetails.getSchoolName());
		return feePaidDetails;
	}


	public FeePaymentResponseDto viewReceipt(String studentId) {
		FeePaymentResponseDto recentPayment = getRecentFeePaymentDetails(studentId);
		return recentPayment;
	}
	

	
	public FeePaymentResponseDto getRecentFeePaymentDetails(String studentId) {
		FeePayment recentPayment = validatePayment(studentId, false);
		try {
		return mapStudentAndPayment(recentPayment);
		}
		catch(FeignException ex) {
			logger.error(Constants.SERVICE_DOWN, ex.getMessage(), ex);
			throw new ClientServiceFailure(ex.getMessage());
		}
		catch(Exception ex) {
			logger.error(Constants.SOMETHING_WENT_WRONG, ex.getMessage(), ex);
			throw new RuntimeException(Constants.SOMETHING_WENT_WRONG);
		}	
	}

	
	private FeePayment validatePayment(String studentId, boolean isPayment) {
		FeePayment recentPayment = null;
		//check if isPayment not true, then show recent payment details
		if(!isPayment) {
			recentPayment = feePaymentRepository.findFirstByStudentIdOrderByIdDesc(studentId)
					.orElseThrow(() -> new PaymentDoesNotExist(Constants.PAYMENT_NOT_FOUND));
			return recentPayment;
		}
		//check if isPayment true check below condition 
		//check current month is later than the recent paid month 
		feePaymentRepository.findFirstByStudentIdOrderByIdDesc(studentId).ifPresent((payment) -> {
			int monthsRemaining = LocalDateTime.now().getMonth().compareTo(
					payment.getPaymentDateTime().getMonth());
			if(monthsRemaining<=0) {
				logger.info(Constants.PAYMENT_ALREADY_EXIST + ", months remaining: " + monthsRemaining);
				throw new PaymentAlreadyExist(Constants.PAYMENT_ALREADY_EXIST);
				}
		});
		return recentPayment;
					
	}
}

