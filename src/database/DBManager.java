package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import basket.BasketItem;
import books.Book;
import books.Fictional;
import books.Genre;
import books.Review;
import orders.Order;
import orders.OrderStatus;
import users.Address;
import users.PostCode;
import users.User;

public class DBManager {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/firstDraft?serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "password";
	
	private DBManager() {}

	public static void deleteAddress(Address address) throws SQLException {
		String sql = "DELETE FROM `address`" + "WHERE address.House_Name/Number = " + address.getHouseName()
				+ "AND address.Postcode =" + address.getPostcode() + ";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
		}
	}

	public static void deleteBasketItem(BasketItem item) throws SQLException {
		String sql = "DELETE FROM `basket_item`" + " WHERE basket_item.ISBN = " + item.getISBN()
				+ " And basket_item.Username = \"" + item.getUserName() + "\";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			stmt.execute();
		}
	}

	public static void deleteBook(Book book) throws SQLException {
		String sql = "DELETE FROM `book`" + "WHERE ISBN = " + book.getISBN() + ";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
		}
	}

	public static void deleteOrder(Order order) throws SQLException {
		String sql = "DELETE FROM `order` WHERE Order_ID = " + order.getId() + ";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
		}
	}

	public static void deleteOrderItems(Order order) throws SQLException {
		String sql = "DELETE FROM `order_item` Order_ID order_id = " + order.getId() + ";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
		}
	}

	public static void deletePostcode(PostCode postCode) throws SQLException {
		String sql = "DELETE FROM `postcode` WHERE postcode = " + postCode.getPostCode() + ";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
		}
	}

	public static void deleteReview(Review review) throws SQLException {
		String sql = "DELETE FROM `book_review` WHERE book_review.Review_ID = " + review.getId() + ";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
		}
	}

	public static void deleteUser(User user) throws SQLException {
		String sql = "DELETE FROM `user` WHERE Username = " + user.getUsername() + ";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
			conn.setAutoCommit(false);
			stmt.execute();
			conn.commit();
		}
	}

	public static void insertAddress(Address address) throws SQLException {
		String sql = "INSERT INTO `address` (`House_Name/Number`, `Postcode`) VALUES (?,?);";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			conn.setAutoCommit(false);
			stmt.setString(1, address.getHouseName());
			stmt.setString(2, address.getPostcode().getPostCode());
			stmt.execute();
			conn.commit();
		}
	}

	public static void insertBasketItems(List<BasketItem> basketItems) throws SQLException {
		String sql = "INSERT INTO `basket_item`(`Username`,`ISBN`)VALUES(?,?);";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			for (BasketItem bi : basketItems) {
				conn.setAutoCommit(false);
				stmt.setString(1, bi.getUserName());
				stmt.setInt(2, bi.getISBN());
				stmt.execute();
				conn.commit();
			}
		}
	}

	public static void insertBooks(List<Book> books) throws SQLException {
		String sql = "INSERT INTO `book`(`ISBN`,`Title`,`Author`,`Synopsis`,"
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

	public static void insertOrders(List<Order> orders) throws SQLException {
		String sql = "INSERT INTO `order`(`Order_ID`,`User_ID`,`Price`,`Date`,`Order_Status`)(?,?,?,?,?);";
		String itemSql = "INSERT INTO `order_item`(`Order_ID`,`Item`)VALUES(?,?);";
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

	public static void insertPostcode(PostCode postCode) throws SQLException {
		String sql = "INSERT INTO `address` (`Postcode`,`Street`,`City`,`Country`) VALUES (?,?,?,?);";
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

	public static void insertReviews(List<Review> reviews) throws SQLException {
		String sql = "INSERT INTO `book_review`(`Review_ID`,`ISBN`,`Username`,`Review`,`Rating`,`Time`)"
				+ "VALUES(?,?,?,?,?,?);";
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

	public static void insertUsers(List<User> users) throws SQLException {
		String sql = "INSERT INTO `user`"
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

	public static List<Address> selectAddress() throws SQLException {
		List<Address> addresses = new ArrayList<>();
		String sql = "SELECT * FROM user;";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				addresses.add(new Address(rs.getString("House_Name/Number"), null));
			}
		}
		return addresses;
	}

	public static List<BasketItem> selectBasketItems() throws SQLException {
		List<BasketItem> items = new ArrayList<>();
		String sql = "SELECT * FROM basket_item;";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				items.add(new BasketItem(rs.getString("Username"), rs.getInt("ISBN")));
			}
		}
		return items;
	}

	public static List<Book> selectBooks() throws SQLException {
		List<Book> books = new ArrayList<>();
		String sql = "SELECT * FROM book;";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				books.add(new Book(rs.getInt("ISBN"), rs.getString("Title"), rs.getString("Author"),
						rs.getString("Synopsis"), rs.getDate("Release Date"), rs.getBoolean("Ebook"),
						rs.getInt("Quantity"), rs.getDouble("Price"), Genre.valueOf(rs.getString("Genre")),
						rs.getString("Edition"), Fictional.valueOf(rs.getString("Fiction/Non-fiction")), null));
			}
		}
		return books;
	}

	public static List<Order> selectOrders() throws SQLException {
		List<Order> orders = new ArrayList<>();
		String sql = "SELECT * FROM order;";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				orders.add(new Order(rs.getInt("Order_ID"), rs.getString("Username"), rs.getDouble("Price"),
						rs.getTimestamp("Time"), OrderStatus.valueOf(rs.getString("Order_Status")), null));
			}
		}
		return orders;
	}

	public static List<PostCode> selectPostCode() throws SQLException {
		List<PostCode> postcodes = new ArrayList<>();
		String sql = "SELECT * FROM postcode;";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				postcodes.add(new PostCode(rs.getString("Postcode"), rs.getString("Street"), rs.getString("City"),
						rs.getString("Country")));
			}
		}
		return postcodes;
	}

	public static List<Review> selectReviews() throws SQLException {
		List<Review> reviews = new ArrayList<>();
		String sql = "SELECT * FROM postcode;";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				reviews.add(new Review(rs.getInt("Review_ID"), rs.getInt("ISBN"), rs.getString("Username"),
						rs.getString("Review"), rs.getInt("Rating"), rs.getTimestamp("Time")));
			}
		}
		return reviews;
	}

	public static List<User> selectUsers() throws SQLException {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM user;";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				users.add(new User(rs.getString("Username"), rs.getString("Password"), null,
						rs.getString("Email_Address"), rs.getString("Contact_Number"), rs.getString("First_Name"),
						rs.getString("Last_Name"), rs.getDate("Date_Of_Birth")));
			}
		}
		return users;
	}

	public static void updateAddress(Address address) throws SQLException {
		String sql = "UPDATE `address`" + "SET" + "`House_Name/Number` = ?," + "`Postcode` = ?"
				+ "WHERE `House_Name/Number` = \"" + address.getHouseName() + "\" AND `Postcode` = \""
				+ address.getPostcode().getPostCode() + "\";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, address.getHouseName());
			stmt.setString(2, address.getPostcode().getPostCode());
			stmt.execute();
		}
	}

	public static void updateBooks(Book book) throws SQLException {
		String sql = "UPDATE `book`" + "SET" + "`ISBN` = ?," + "`Title` = ?," + "`Author` = ?," + "`Synopsis` = ?,"
				+ "`Release Date` = ?," + "`EBook` = ?," + "`Quantity` = ?," + "`Price` = ?," + "`Genre` = ?,"
				+ "`Edition` = ?," + "`Fiction/Non-fiction` = ?" + "WHERE `ISBN` = " + book.getISBN() + ";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);) {
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

	public static void updatePostcode(PostCode postCode) throws SQLException {
		String sql = "UPDATE `postcode`" + "SET" + "`Postcode` = ?," + "`Street` = ?," + "`City` = ?," + "`Country` = ?"
				+ "WHERE `Postcode` = " + postCode.getPostCode() + ";";
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

	public static void updateUsers(User user) throws SQLException {
		String sql = "UPDATE `user`" + "SET" + "`Username` = ?," + "`Password` = ?," + "`House Name/Number` = ?,"
				+ "`Postcode` = ?," + "`Email_Address` = ?," + "`Contact_Number` = ?," + "`First_Name` = ?,"
				+ "`Last_Name` = ?," + "`Date_Of_Birth` = ?" + "WHERE `Username` = " + user.getUsername() + ";";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				PreparedStatement stmt = conn.prepareStatement(sql);) {
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
