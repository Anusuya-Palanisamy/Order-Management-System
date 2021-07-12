package com.order.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Products")
public class Product {
	
	@Id
	String product_Id;
	String productName;
	String description;
	int price;	
	public Address address;
	
	

}
