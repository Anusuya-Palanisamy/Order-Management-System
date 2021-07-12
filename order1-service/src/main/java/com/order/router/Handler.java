package com.order.router;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.order.model.Order;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class Handler {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	public Mono<ServerResponse> createOrder(ServerRequest serverRequest) {
		Mono<Order> orderMono=serverRequest.bodyToMono(Order.class).flatMap(orderService::saveOrder);
		return ServerResponse.status(HttpStatus.CREATED)
				.body(orderMono,Order.class);
				
	}
	
	public Mono<ServerResponse> billData(ServerRequest serverRequest) {
		String orderid=serverRequest.pathVariable("order_id");
		return orderService.findByIdBill(orderid)
				.flatMap(orderData -> ServerResponse.ok()
				.bodyValue(orderData))
				.switchIfEmpty(ServerResponse.notFound().build());

	}
	
	public Mono<ServerResponse> orderListDate(ServerRequest serverRequest) {
		String startDate=serverRequest.pathVariable("startDate");
		String endDate=serverRequest.pathVariable("endDate");
		Flux<Order> list=orderService.orderListDateRange(startDate, endDate);
		return ServerResponse.ok().body(list, Order.class);
	}
	public Mono<ServerResponse> updateStatus(ServerRequest serverRequest) {
		String order_id=serverRequest.pathVariable("order_id");
		String status=serverRequest.pathVariable("status");
		return orderService.findById(order_id).flatMap(saveOrder -> {
			saveOrder.setStatus(status);
			return orderRepository.save(saveOrder);
		}).flatMap(orderData -> ServerResponse.ok()
				.bodyValue(orderData))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> OderListBasedonCity(ServerRequest serverRequest) {
		String city=serverRequest.pathVariable("city");
		
		Flux<Order> list=orderService.orderListByCity(city);
		return ServerResponse.ok().body(list, Order.class);
		
	}
	
	public Mono<ServerResponse> orderListByDate(ServerRequest serverRequest) {
		String date=serverRequest.pathVariable("date");
		String status=serverRequest.pathVariable("status");
		Flux<Order> list=orderService.OrderListByDateAndStatus(date, status);
		return ServerResponse.ok().body(list, Order.class);
	 
	}
	
	public Mono<ServerResponse> OderListBasedonData(ServerRequest serverRequest) {	
		String date=serverRequest.pathVariable("date");
		Flux<Order> list=orderService.orderListBasedDate(date);
		return ServerResponse.ok().body(list, Order.class);
				
	}
	

	
	public Mono<ServerResponse> getListOrder(ServerRequest serverRequest){
		Flux<Order> list=orderService.orderList();
		return ServerResponse.ok().body(list, Order.class);
		
	}

}
