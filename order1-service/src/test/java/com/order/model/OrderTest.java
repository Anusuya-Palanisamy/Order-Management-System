package com.order.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class OrderTest {
	@Test
	public void setOrderTest() {
		Order order=new Order();
		order.setOrder_id("609647c05881436283264a9b");
        order.setCustomerId("32543554524354654667a");
		order.setDate("2021/05/08");
		Product product = new Product();
		product.setProduct_Id("353478654358c");
		product.setDescription("Good");
		product.setProductName("Phone");
		product.setPrice(1213);
		Address address=new Address();
		address.setAddressDetails("3/138, Moongiltholuvu");
		address.setAddressid("243875437a");
		address.setCity("cbe");
		address.setPincode(642202);
		address.setState("TamilNadu");
		List<Address> addressList=new ArrayList<Address>();
		addressList.add(address);
		List<Product> productList=new ArrayList<Product>();
		productList.add(product);
		order.setProduct(productList);
		order.setStatus("approved");
		order.setTotal(3254355);
		assertEquals("609647c05881436283264a9b", order.getOrder_id());
	    assertEquals("32543554524354654667a", order.getCustomerId());
	    assertEquals(productList,order.getProduct());
	    assertEquals("approved",order.getStatus());
	}

}
