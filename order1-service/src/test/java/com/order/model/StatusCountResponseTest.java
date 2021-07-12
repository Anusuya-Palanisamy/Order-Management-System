package com.order.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class StatusCountResponseTest {
	@Test
	public void setStatusCountResponseTest() {
		StatusCountResponse statusCountResponse=new StatusCountResponse();
		statusCountResponse.setCountApproved(7);
		statusCountResponse.setCountDenied(3);
		statusCountResponse.setCountPending(7);
		assertEquals(7,statusCountResponse.getCountApproved());
		assertEquals(3,statusCountResponse.getCountDenied());
		assertEquals(7,statusCountResponse.getCountPending());
		
	}

}
