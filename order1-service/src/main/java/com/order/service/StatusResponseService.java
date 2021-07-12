package com.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.model.StatusCountResponse;
import com.order.repository.OrderRepository;

import reactor.core.publisher.Mono;

@Service
public class StatusResponseService {

	/**
	 * SUPER_Admin Use cases
	 */
	@Autowired
	OrderRepository orderRepository;

	

	public Mono<StatusCountResponse> statusCount(String date) {

		Mono<Long> countOfApproved = orderRepository.countStatusByDate(date, "approved");
		Mono<Long> countOfPending = orderRepository.countStatusByDate(date, "pending");
		Mono<Long> countOfDenied = orderRepository.countStatusByDate(date, "denied");
		StatusCountResponse stausCount = new StatusCountResponse();
		countOfPending.subscribe(x -> stausCount.setCountPending(x));
		countOfApproved.subscribe(y -> stausCount.setCountApproved(y));
		countOfDenied.subscribe(z -> stausCount.setCountDenied(z));
		return Mono.just(stausCount);

	}

}
