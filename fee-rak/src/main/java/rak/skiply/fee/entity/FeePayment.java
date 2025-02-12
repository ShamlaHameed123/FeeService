package rak.skiply.fee.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "fee_payment")
@Data
public class FeePayment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "STUDENT_ID", nullable=false)
    private String studentId;

//    @Column(name = "STUDENT_NAME", nullable=false)
//    private String studentName;
//
//    @Column(name = "GRADE", nullable=false)
//    private int grade;

    @Column(name = "CARD_TYPE", nullable=false)
    private String cardType;

    @Column(name= "CARD_NUMBER", nullable=false)
    private String cardNumber;
    
    @Column(name= "REFERENCE_NUMBER", nullable=false)
    private String referenceNumber;
    
    @Column(name= "TUITION_FEES", nullable=false)
    private BigDecimal tuitionFees;
    
    @Column(name= "CUSTOM_AMOUNT", nullable=false)
    private BigDecimal customAmount;
    
    @Column(name= "CURRENCY",nullable=false)
    private String currency;
    
    @Column(name = "PAYMENT_DATE_TIME", nullable=false)
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime paymentDateTime;
    
    @Column(name= "NO_OF_PAYMENTS", nullable=false)
    private int noOfPayments;
    
    
    
    

}
