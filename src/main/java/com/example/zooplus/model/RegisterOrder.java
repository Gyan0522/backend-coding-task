package com.example.zooplus.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Entity
public class RegisterOrder {

	private @Id @GeneratedValue Long id;
	
	
	private String orderId;
	private String customerId;
	private Integer amount;

	public RegisterOrder() {
	}

	public static String created(HttpStatus CREATED) {
		// TODO Auto-generated method stub
		final String successCode = "201";
		return successCode;
	}


	public ResponseEntity<RegisterOrder> orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}