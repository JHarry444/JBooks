package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

import books.Book;
import books.Review;
import orders.Order;
import users.Address;
import users.PostCode;
import users.User;

public class DBManager {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/firstDraft";
	static final String USER = "root";
	static final String PASS = "password";

	public static void insertBooks(List<Book> books) throws SQLException {
		String sql = "INSERT INTO `firstdraft`.`book`(`ISBN`,`Title`,`Author`,`Synopsis`,"
				+ "`Release Date`,`EBook`,`Quantity`,`Price`,`Genre`,`Edition`,"
				+ "`Fiction/Non-fiction`)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
			for (Book book : books) {
				try (PreparedStatement stmt = conn.prepareStatement(sql);) {
					conn.setAutoCommit(false);
					stmt.setInt(1, book.getISBN());
					stmt.setString(2, book.getTitle());
					stmt.setString(3, book.getAuthor());
					stmt.setString(4, book.getSynopsis());
					stmt.setDate(5, book.getReleaseDate());
					stmt.setBoolean(6, book.isEBook());
					stmt.setInt(7, book.getQuantity());
					stmt.setDouble(8, book.getPrice());
					stmt.setString(9, book.getGenre().name());
					stmt.setString(10, book.getEdition());
					stmt.setString(11, book.getFictional().name());
					stmt.execute();
					conn.commit();
				}
			}
		}
	}

	public static void insertUsers(List<User> users) throws SQLException {
		String sql = "INSERT INTO `firstdraft`.`user`"
				+ "(`Username`,`Password`,`House Name/Number`,`Postcode`,`Email_Address`,`Contact_Number`,`First_Name`,`Last_Name`,`Date_Of_Birth`)"
				+ "?,?,?,?,?,?,?,?,?);";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
			for (User user : users) {
				try (PreparedStatement stmt = conn.prepareStatement(sql);) {
					conn.setAutoCommit(false);
					stmt.setString(1, user.getUsername());
					stmt.setString(2, user.getPassword());
					stmt.setString(3, user.getAddress().getHouseName());
					stmt.setString(4, user.getAddress().getPostcode().getPostCode());
					stmt.setString(5, user.getEmail());
					stmt.setString(6, user.getContactNumber());
					stmt.setString(7, user.getFirstName());
					stmt.setString(8, user.getLastName());
					stmt.setDate(9, user.getBirthDate());
					stmt.execute();
					conn.commit();
				}
			}
		}
	}

	public static void insertAddress(Address address) throws SQLException {
		String sql = "INSERT INTO `firstdraft`.`address` (`House_Name/Number`, `Postcode`) VALUES (?,?);";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);
			stmt.setString(1, address.getHouseName());
			stmt.setString(2, address.getPostcode().getPostCode());
			stmt.execute();
			conn.commit();
		}
	}

	public static void insertPostcode(PostCode postCode) throws SQLException {
		String sql = "INSERT INTO `firstdraft`.`address` (`Postcode`,`Street`,`City`,`Country`) VALUES (?,?,?,?);";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);
			stmt.setString(1, postCode.getPostCode());
			stmt.setString(1, postCode.getStreet());
			stmt.setString(1, postCode.getCity());
			stmt.setString(1, postCode.getCountry());
			stmt.execute();
			conn.commit();
		}
	}

	public static void insertOrders(List<Order> orders) throws SQLException {
		String sql = "INSERT INTO `firstdraft`.`order`(`Order_ID`,`User_ID`,`Price`,`Date`,`Order_Status`)(?,?,?,?,?);";
		String itemSql = "INSERT INTO `firstdraft`.`order_item`(`Order_ID`,`Item`)VALUES(?,?);";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			for (Order order : orders) {
				conn.setAutoCommit(false);
				stmt.setInt(1, order.getId());
				stmt.setString(2, order.getUsername());
				stmt.setDouble(3, order.getPrice());
				stmt.setTimestamp(4, order.getTime());
				stmt.setString(5, order.getOrderStatus().name());
				stmt.execute();
				for (int item : order.getItems()) {
					try (PreparedStatement orderStmt = conn.prepareStatement(itemSql)) {
						stmt.setInt(1, order.getId());
						stmt.setInt(2, item);
						stmt.execute();
					}
				}
				conn.commit();
			}
		}
	}
	
	public static void insertReviews(List<Review> reviews) throws SQLException {
		String sql = "INSERT INTO `firstdraft`.`book_review`(`Review_ID`,`ISBN`,`Username`,`Review`,`Rating`,`Time`)VALUES(<{Review_ID: }>,<{ISBN: }>,<{Username: }>,<{Review: }>,<{Rating: }>,<{Time: CURRENT_TIMESTAMP}>);";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			for (Review review : reviews) {
				conn.setAutoCommit(false);
				stmt.setInt(1, review.getId());
				stmt.setString(2, review.getUsername());
				stmt.setString(3, review.getReview());
				stmt.setInt(4, review.getRating());
				stmt.setTimestamp(5, review.getTimestamp());
				stmt.execute();
				conn.commit();
			}
		}
	}
	
	public static void insertBasketItems(Map<String, Integer> basketItems) throws SQLException {
		String sql = "INSERT INTO `firstdraft`.`basket_item`(`Username`,`ISBN`)VALUES(<{Username: }>,<{ISBN: }>);";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			for (Entry<String, Integer> entry : basketItems.entrySet()) {
				conn.setAutoCommit(false);
				stmt.setString(2, entry.getKey());
				stmt.setInt(4, entry.getValue());
				stmt.execute();
				conn.commit();
			}
		}
	}
	
}
