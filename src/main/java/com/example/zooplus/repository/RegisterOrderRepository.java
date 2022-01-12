package com.example.zooplus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.zooplus.model.Payments;
import com.example.zooplus.model.RegisterOrder;

@SuppressWarnings("rawtypes")
public interface RegisterOrderRepository extends JpaRepository<RegisterOrder, Long> {

	Object save(Payments createPayment);

	@Query("select u from RegisterOrder u where u.orderId =?1 ")
	List getOrderBalance(String orderId);

	@Query("select u from RegisterOrder u where u.customerId =?1 ")
	List getCustomerBalance(String customerId);

}