package com.order.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "address")
public class Address {

	/*
	 * Data declared
	 */
	
	@Id
	private String addressid;
	public String addressDetails;
	public String city;
	public String state;
	public long pincode;
}
