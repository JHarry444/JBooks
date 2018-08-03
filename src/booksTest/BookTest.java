package booksTest;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import books.Book;
import books.Fictional;
import books.Genre;
import books.Review;

public class BookTest {
	@Test
	public void testGetISBN() {
		Book book = new Book.BookBuilder().ISBN(4444).buildBook();
		assertEquals("Incorrect ISBN", 4444, book.getISBN());
	}

	@Test
	public void testSetISBN() {
		Book book = new Book();
		book.setISBN(4444);
		assertEquals("Incorrect ISBN", 4444, book.getISBN());
	}

	@Test
	public void testGetTitle() {
		Book book = new Book.BookBuilder().Title("Test").buildBook();
		assertEquals("Incorrect title.", "Test", book.getTitle());
	}

	@Test
	public void testSetTitle(String title) {
		Book book = new Book();
		book.setTitle("Title");
		assertEquals("Incorrect title.", "Test", book.getTitle());
	}

	@Test
	public void testGetAuthor() {
		Book book = new Book.BookBuilder().Author("Test").buildBook();
		assertEquals("Incorrect author.", "Test", book.getAuthor());
	}

	@Test
	public void testSetAuthor(String author) {
		Book book = new Book();
		book.setAuthor("Test");
		assertEquals("Incorrect author.", "Test", book.getAuthor());
	}

	@Test
	public void testGetSynopsis() {
		return synopsis;
	}

	@Test
	public void testSetSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Test
	public Date testGetReleaseDate() {
		return releaseDate;
	}

	@Test
	public void testSetReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Test
	public boolean isEBook() {
		return eBook;
	}

	@Test
	public void testSeteBook(boolean eBook) {
		this.eBook = eBook;
	}

	@Test
	public void testGetQuantity() {
		return quantity;
	}

	@Test
	public void testSetQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Test
	public double testGetPrice() {
		return price;
	}

	@Test
	public void testSetPrice(double price) {
		this.price = price;
	}

	@Test
	public Genre testGetGenre() {
		return genre;
	}

	@Test
	public void testSetGenre(Genre genre) {
		this.genre = genre;
	}

	@Test
	public void testGetEdition() {
		return edition;
	}

	@Test
	public void testSetEdition(String edition) {
		this.edition = edition;
	}

	@Test
	public Fictional testGetFictional() {
		return fictional;
	}

	@Test
	public void testSetFictional(Fictional fictional) {
		this.fictional = fictional;
	}

	@Test
	public List<Review> testGetReviews() {
		return reviews;
	}

	@Test
	
	public void testSetReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}
