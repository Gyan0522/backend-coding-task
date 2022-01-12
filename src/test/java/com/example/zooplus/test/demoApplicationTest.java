package com.example.zooplus.test;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.zooplus.controller.OrderandPaymentTrackerController;
import com.example.zooplus.repository.RegisterOrderRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OrderandPaymentTrackerController.class)
public class demoApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderandPaymentTrackerController orderandpaymenttrackerController;

	private final RegisterOrderRepository repository = null;

	@Test
	public void contextLoads() {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void retrieveDetailsForOrder() throws Exception {

		ResponseEntity<Map> mockOrder = null;

		Mockito.when(orderandpaymenttrackerController.getOrderBalance(Mockito.anyString())).thenReturn(mockOrder);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/order/balance")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{}";

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}