package com.order.router;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.order.model.Order;
import com.order.model.StatusCountResponse;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import com.order.service.StatusResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apis")
@Api(value = "Order", description = "Operations Order Management System")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	StatusResponseService statusService;

	@PostMapping("/CreateOrder")
	@ApiOperation(value = "Create Customer Order Request")
	public Mono<ResponseEntity<Order>> createOrder(@RequestBody Order order) {
		return orderService.saveOrder(order).map(orderData -> new ResponseEntity<>(order, HttpStatus.CREATED))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/invoiceData")
	@ApiOperation(value = "view InvoiceData")
	public Mono<ResponseEntity<Order>> billData(@RequestParam(name = "order_id", required = true) String order_id) {
		return orderService.findByIdBill(order_id).map(order -> ResponseEntity.ok(order))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@GetMapping("/dateRange")
	@ApiOperation(value = "Get order details by date range")
	public Flux<Order> orderListDateRange(@RequestParam(name = "startDate", required = true) String startDate,
			@RequestParam(name = "endDate", required = true) String endDate) {
		return orderService.orderListDateRange(startDate, endDate);
				//.map(order ->  new ResponseEntity<>(order, HttpStatus.OK))
				//.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PutMapping("/updateById")
	@ApiOperation(value = " Update order Status")
	public Mono<ResponseEntity<Order>> updateStatus(@RequestParam(name = "order_id", required = true) String order_id,
			@RequestParam(name = "status", required = true) String status) {
		return orderService.findById(order_id).flatMap(saveOrder -> {
			saveOrder.setStatus(status);
			return orderRepository.save(saveOrder);
		}).map(updateOrder -> new ResponseEntity<>(updateOrder, HttpStatus.OK))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/cityname")
	@ApiOperation(value = "Get order details by shipping city")
	public Flux<Order> OderListBasedonCity(@RequestParam(name = "city", required = true) String city) {
		return orderService.orderListByCity(city);
	}

	@GetMapping("/dateAndStaus")
	@ApiOperation(value = " Get the orders based on date & order status")
	public Flux<Order> orderListByDate(@RequestParam(name = "date", required = true) String date,
			@RequestParam(name = "status", required = true) String status) {
		return orderService.OrderListByDateAndStatus(date, status);
	 
	}

	@GetMapping("/dateByOrder")
	@ApiOperation(value = " Get the order details by date")
	public Flux<Order> OderListBasedonData(@RequestParam(name = "date", required = true) String date) {
		return orderService.orderListBasedDate(date);
				
	}
	
	@GetMapping("/StatusCount")
	@ApiOperation(value = " Get the Status Count based on the Date")
	public Mono<ResponseEntity<StatusCountResponse>> statusCount(@RequestParam String date) {
		return statusService.statusCount(date).map(updateOrder -> new ResponseEntity<>(updateOrder, HttpStatus.OK))
				.defaultIfEmpty(ResponseEntity.notFound().build());

	}

	@GetMapping("/orders")
	@ApiOperation(value = "Get the list of all Orders")
	public Flux<Order> OrderList() {
		return orderService.orderList();
	}
}
