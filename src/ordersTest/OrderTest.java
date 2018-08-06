package ordersTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import orders.Order;
import orders.OrderStatus;

public class OrderTest {

	private Order order = new Order(4, "test", 44.44, new Timestamp(0), OrderStatus.COMPLETE, Arrays.asList(4, 14, 24, 24, 44, 54, 64, 74, 84, 94));

/*	public void testGetId() {
		return id;
	}

	public void testSetId() {
		this.id = id;
	}

	public String testGetUsername() {
		return username;
	}

	public void testSetUsername(String username) {
		this.username = username;
	}

	public double testGetPrice() {
		return price;
	}

	public void testSetPrice(double price) {
		this.price = price;
	}

	public Timestamp testGetTime() {
		return time;
	}

	public void testSetTime(Timestamp time) {
		this.time = time;
	}

	public OrderStatus testGetOrderStatus() {
		return orderStatus;
	}

	public void testSetOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<Integer> testGetItems() {
		return items;
	}

	public void testSetItems(List<Integer> items) {
		this.items = items;
	}*/
}
