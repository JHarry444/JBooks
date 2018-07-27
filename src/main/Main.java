package main;

import java.sql.SQLException;
import java.util.Arrays;
import java.sql.Date;

import books.Book;
import books.Fictional;
import books.Genre;
import database.DBManager;

public class Main {

	public static void main(String[] args) throws SQLException {
		DBManager.insertBooks(Arrays.asList(new Book.BookBuilder().Title("test").Fictional(Fictional.FICTION).ISBN(44).ReleaseDate(new Date(new java.util.Date().getTime())).Author("me").eBook(false).Edition("1st").Genre(Genre.MYSTERY).Price(44.44).Quantity(444).buildBook()));
	}
}
