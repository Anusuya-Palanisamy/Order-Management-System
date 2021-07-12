package com.order.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.model.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface OrderService {

	Mono<Order> saveOrder(Order order);

	Mono<Order> findByIdBill(String order_id);

	Flux<Order> orderListDateRange(String startDate, String endDate);

	Mono<Order> findById(String order_id);

	Flux<Order> OrderListByDateAndStatus(String date, String status);

	Flux<Order> orderListByCity(String city);

	Flux<Order> orderListBasedDate(String date);

	Flux<Order> orderList();

	

}
