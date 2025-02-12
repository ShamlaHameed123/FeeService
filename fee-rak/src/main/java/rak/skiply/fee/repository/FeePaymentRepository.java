package rak.skiply.fee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rak.skiply.fee.entity.FeePayment;



@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Integer> {

	public List<FeePayment> findByStudentId(String studentId);
	public Optional<FeePayment> findFirstByStudentIdOrderByIdDesc(String studentId);
	public Optional<FeePayment> findByReferenceNumber(String referenceNumber);
}
