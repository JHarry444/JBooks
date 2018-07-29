package main;

import java.sql.SQLException;
import java.util.Arrays;

import basket.BasketItem;

import java.sql.Date;

import books.Book;
import books.Fictional;
import books.Genre;
import database.DBManager;
import users.Address;
import users.PostCode;

public class Main {

	public static void main(String[] args) throws SQLException {
//		DBManager.insertBooks(Arrays.asList(new Book.BookBuilder().Title("test").Fictional(Fictional.FICTION).ISBN(44).ReleaseDate(new Date(new java.util.Date().getTime())).Author("me").eBook(false).Edition("1st").Genre(Genre.MYSTERY).Price(44.44).Quantity(444).buildBook()));
		PostCode pc = new PostCode();
		pc.setPostCode("WS11 6XJ");
		DBManager.updateAddress(new Address("test", pc));
	}
}
