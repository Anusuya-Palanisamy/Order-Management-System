package com.order.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Order")
public class Order implements Serializable{
	

	@Id
	public String order_id;

	private  String customerId;
    
	private  String date;
	
	private  List<Product> product;
	
	private  List<InvoiceData> invoiceData;
	
	private double total;
	
	private  String status;
}
