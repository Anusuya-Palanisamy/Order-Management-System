package com.order.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public class StatusCountResponse {

	private long countApproved;
	private long countPending;
	private long countDenied;
	


}
