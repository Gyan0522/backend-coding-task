package com.example.zooplus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.zooplus.exception.DataNotFoundException;
import com.example.zooplus.model.Payments;
import com.example.zooplus.model.RegisterOrder;
import com.example.zooplus.repository.PaymentsRepository;
import com.example.zooplus.repository.RegisterOrderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class OrderandPaymentTrackerController {

	private final RegisterOrderRepository repository;
	private final PaymentsRepository paymentRepository;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	OrderandPaymentTrackerController(RegisterOrderRepository repository, PaymentsRepository paymentRepository) {
		this.repository = repository;
		this.paymentRepository = paymentRepository;
	}
    
	@PostMapping("v1/order/create")
	String createOrder(@RequestBody RegisterOrder createOrder) {
		String returnCode = null;
		if (repository.save(createOrder) != null) {
			returnCode = RegisterOrder.created(HttpStatus.CREATED);
		}

		return returnCode;
	}

	@PostMapping("v1/payment/create")
	String createPayment(@RequestBody Payments createPayment) {
		String returnCode = null;

		if (repository.save(createPayment) != null) {
			returnCode = Payments.created(HttpStatus.CREATED);
		}

		return returnCode;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/v1/customer/balance")
	ResponseEntity<Map> getCustomerBalance(@RequestParam(required = false) String customerId) {

		ArrayList<Object> listOrderAmount = new ArrayList<>();
		ArrayList<Object> listPaymentAmount = new ArrayList<>();

		listOrderAmount = (ArrayList<Object>) repository.getCustomerBalance(customerId);
		Integer strOrderAmount = 0;
		Integer strOrderAmountTemp = 0;
		String orderId = null;
		Integer strPaymentAmount = 0;
		Integer strPaymentAmounttemp = 0;
		if (!(listOrderAmount.size() == 0)) {
			for (int i = 0; i < listOrderAmount.size(); i++) {

				RegisterOrder tempObj = (RegisterOrder) listOrderAmount.get(i);
				strOrderAmountTemp = tempObj.getAmount();
				strOrderAmount = strOrderAmount + strOrderAmountTemp;

				orderId = tempObj.getOrderId();

				listPaymentAmount = (ArrayList<Object>) paymentRepository.getPaymentBalance(orderId);

				if (!(listPaymentAmount.size() == 0)) {

					Payments tempObj1 = (Payments) listPaymentAmount.get(0);
					strPaymentAmounttemp = tempObj1.getAmount();
					strPaymentAmount = strPaymentAmount + strPaymentAmounttemp;

				}
			}

		}

		if (listOrderAmount.size() == 0) {
			throw new DataNotFoundException();
		}

		Map<String, String> customerBalanceList = new HashMap<>();
		customerBalanceList.put("customerId", customerId);
		Integer BalanceAmount = 0;

		if (strOrderAmount > strPaymentAmount) {
			BalanceAmount = strOrderAmount - strPaymentAmount;
			customerBalanceList.put("amount", "-" + BalanceAmount);
		} else {
			BalanceAmount = strPaymentAmount - strOrderAmount;
			customerBalanceList.put("amount", "+" + BalanceAmount);

		}

		return ResponseEntity.ok().body(customerBalanceList);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/v1/order/balance")
	public ResponseEntity<Map> getOrderBalance(@RequestParam(required = false) String orderId) {

		ArrayList<Object> listOrderAmount = new ArrayList<>();
		ArrayList<Object> listPaymentAmount = new ArrayList<>();

		listOrderAmount = (ArrayList<Object>) repository.getOrderBalance(orderId);
		Integer orderAmount = 0;

		if (!(listOrderAmount.size() == 0)) {
			RegisterOrder tempObj = (RegisterOrder) listOrderAmount.get(0);
			orderAmount = tempObj.getAmount();
			
		}

		if (listOrderAmount.size() == 0) {
			throw new DataNotFoundException();
		}

		listPaymentAmount = (ArrayList<Object>) paymentRepository.getPaymentBalance(orderId);

		Integer strPaymentAmount = 0;
		if (!(listPaymentAmount.size() == 0)) {
			Payments tempObj1 = (Payments) listPaymentAmount.get(0);
			strPaymentAmount = tempObj1.getAmount();

		}

		Map<String, String> customerBalanceList = new HashMap<>();
		customerBalanceList.put("orderId", orderId);
		Integer BalanceAmount = 0;

		if (orderAmount > strPaymentAmount) {
			BalanceAmount = orderAmount - strPaymentAmount;

			customerBalanceList.put("amount", "-" + BalanceAmount);
		} else {

			BalanceAmount = strPaymentAmount - orderAmount;

			customerBalanceList.put("amount", "+" + BalanceAmount);

		}

		return ResponseEntity.ok().body(customerBalanceList);
	}

}
