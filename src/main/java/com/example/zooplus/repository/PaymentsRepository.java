package com.example.zooplus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.zooplus.model.Payments;
import com.example.zooplus.model.RegisterOrder;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {

	@Query("select u from Payments u where u.orderId =?1 ")
	List getPaymentBalance(String orderId);

}