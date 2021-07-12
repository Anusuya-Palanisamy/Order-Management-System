package com.order.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.order.model.InvoiceData;
import com.order.model.Order;
import com.order.model.Product;
import com.order.model.StatusCountResponse;
import com.order.repository.OrderRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderServiceImpl implements OrderService {
	protected static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrderRepository orderRepository;

	/**
	 * Customer Use cases
	 */
	@Override
	public Mono<Order> saveOrder(Order order) {
		LOGGER.debug("customer save the order Details", order);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		String dateFormat = dtf.format(now);
		List<Product> product = order.getProduct();
		List<InvoiceData> invoicedata = new ArrayList<InvoiceData>();
		
		for (Product productData : product) {
			InvoiceData invoice = new InvoiceData();
			invoice.setProductName(productData.getProductName());
			invoice.setPrice(productData.getPrice());
			order.setTotal(order.getTotal()+productData.getPrice());
			invoicedata.add(invoice);
			order.setInvoiceData(invoicedata);
		}

		order.setStatus("pending");
		order.setDate(dateFormat);
		return orderRepository.save(order);
	}

	/**
	 * Customer Use cases
	 */
	@Override
	public Mono<Order> findByIdBill(String order_id) {
		LOGGER.debug("System Admin find the order List based on the Order Id", order_id);
		return orderRepository.findByOrderId(order_id);
	}

	/**
	 * System_Admin, SUPER_Admin Use cases
	 */
	@Override
	public Flux<Order> orderListDateRange(String startDate, String endDate) {
		LOGGER.debug("Super Admin find the order List based on the Date Range", startDate, endDate);
		return orderRepository.findByDateBetweenAndAsc(startDate, endDate);
	}

	/**
	 * System_Admin Use cases
	 */
	@Override
	public Mono<Order> findById(String order_id) {
		LOGGER.debug("System Admin find the order List based on the Order Id", order_id);
		return orderRepository.findById(order_id);
	}

	/**
	 * SUPER_Admin, SYSTEM_DMIN Use cases
	 */
	@Override
	public Flux<Order> orderList() {
		LOGGER.debug("List out the all order Details");
		return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
	}
	//

	/**
	 * SUPER_Admin Use cases
	 */
	@Override
	public Flux<Order> orderListByCity(String city) {
		LOGGER.debug("System Admin find the order List based on the city name", city);
		return orderRepository.findOrderByProductAddressCity(city);
	}

	/**
	 * SUPER_Admin Use cases
	 */
	@Override
	public Flux<Order> OrderListByDateAndStatus(String date, String status) {
		LOGGER.debug("Super Admin find the order List based on the Date and Status", date, status);
		return orderRepository.findByDateAndStatus(date, status);
	}

	/**
	 * Super_Admin Use cases
	 */
	@Override
	public Flux<Order> orderListBasedDate(String date) {
		LOGGER.debug("System Admin find the order List based on the data");
		return orderRepository.findByDate(date);
	}

	
}
