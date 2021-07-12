package com.order.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.order.model.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
	
	
	//Flux<Order> findByCustomerId(String customerId);
	@Query(value = "{order_id : ?0}", fields = "{ 'invoiceData' : 1, 'total' : 1}")
	Mono<Order> findByOrderId(String order_id);

	Flux<Order> findByDate(String date);

	Flux<Order> findOrderByProductAddressCity(String city);

	Flux<Order> findByDateAndStatus(String date, String status);
	
	@Query(value ="{'date' : { $gte: ?0, $lte: ?1 } }", sort = "{date : 1}")  
	Flux<Order> findByDateBetweenAndAsc(String startDate, String endDate);
	
	@Query("[ { $match: { date: date } }, {total :{ $sum: $total } }]")
	Mono<Long> findBySumOfPrice();
	
	@Query(value = "{date : ?0, status : ?1}", count = true)
	Mono<Long> countStatusByDate(String date,String status);
	
	@Query("[ { $match:?0 }, {total :{ $sum: $total } }]")
	Mono<Double> getSumOfTotalByDate(String date);

	
}
