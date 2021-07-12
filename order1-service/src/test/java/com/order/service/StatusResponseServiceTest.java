package com.order.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import com.order.model.Address;
import com.order.model.InvoiceData;
import com.order.model.Order;
import com.order.model.Product;
import com.order.model.StatusCountResponse;
import com.order.repository.OrderRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@DataMongoTest
@Import(StatusResponseService.class)
public class StatusResponseServiceTest {
	
	
	@MockBean
	StatusResponseService statusResponse;
	
	@MockBean
	OrderRepository orderRepository;
	
	@Test
	public void orderStatusTest() {
		
	StatusCountResponse statusCountResponse=new StatusCountResponse();
		statusCountResponse.setCountApproved(0);
		statusCountResponse.setCountDenied(3);
		statusCountResponse.setCountPending(3);
		Mockito.when(statusResponse.statusCount("2021/05/08"))
		.thenReturn(Mono.just(statusCountResponse));
		assertEquals(3, statusCountResponse.getCountPending());
		
	    
	}
	
}
