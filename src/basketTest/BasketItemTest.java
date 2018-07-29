package basketTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import basket.BasketItem;

public class BasketItemTest {

	@Test
	public void getterSetterISBNTest() {
		BasketItem basketItem = new BasketItem();
		basketItem.setISBN(4444);
		assertEquals("Incorrect value for ISBN", 4444, basketItem.getISBN());
	}

	@Test
	public void getterSetterUserNameTest() {
		BasketItem basketItem = new BasketItem();
		basketItem.setUserName("Test");
		assertEquals("Incorrect value for userName.", "Test", basketItem.getUserName());
	}

}
