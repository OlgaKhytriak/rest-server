package com.epam.dao;

import java.util.ArrayList;
import java.util.Iterator;

import com.epam.model.Book;

public class BookDAO {
	
	private static ArrayList<Book> BOOKS;
	
	public BookDAO() {
		init();
	}
	
	private void init(){
		BOOKS = new ArrayList<>();
		BOOKS.add(new Book("The Sacred Sword", "Scott Mariani", "Action & Adventure",1));
		BOOKS.add(new Book("The Diary of a Snake ", "Scott Mariani", "Adventure",2));
		BOOKS.add(new Book("And Then There Were None", "Agatha Christie", "Thriller",3));
		BOOKS.add(new Book("Inferno", "Dan Brown", "Thriller",4));
		BOOKS.add(new Book("Life is What You Make it", "Preeti Shenoy", "Romance",5));
		BOOKS.add(new Book("Pride and Prejudice", "Jane Austen", "Romance",6));
		BOOKS.add(new Book("Crystal Magic", "Madeline Freeman", "Fantasy",7));
		BOOKS.add(new Book("The Invisible Man", "H.G. Wells", "Fantasy",8));
		BOOKS.add(new Book("The Motorcycle Diaries", "Ernesto 'Che' Guevara", "Travel",9));
		BOOKS.add(new Book("Life is What You Make it", "Mark Twain", "Travel",10));
	}
	
	public void add(Book book){
			BOOKS.add(book);
	}
	
	public boolean contains(Book book){
		return BOOKS.contains(book);
	}
	
	public boolean containsById(Book book){
		for (Book b : BOOKS) {
			if(b.getId().equals(book.getId())){
				return true;
			}
		}
		return false;
	}
	
	public void delete(Book book){
		BOOKS.remove(book);
	}
	
	public boolean delete(Integer id){
		Iterator<Book> iter = BOOKS.iterator();
		while (iter.hasNext()) {
			Book book= iter.next();
			if(book.getId().equals(id)){
				iter.remove();
				return true;
			}
		}
		return false;
	}
	
	public void update(Book oldBook,Book newBook){
		Iterator<Book> iter = BOOKS.iterator();
		boolean oldBookDeleted = false;
		while (iter.hasNext()) {
			if(iter.next().getId().equals(oldBook.getId())){
				iter.remove();
				oldBookDeleted = true;
			}
		}
		if(oldBookDeleted){
			BOOKS.add(newBook);
		}
		
	
	}
	
	public ArrayList<Book> getAll(){
		return BOOKS;
	}
	
	public Book get(Integer id){
		for (Book book : BOOKS) {
			if(book.getId().equals(id)){
				return book;
			}
		}
		return null;
	}
	
	public ArrayList<Book> getAllByName(String name){
		ArrayList<Book> list = new ArrayList<>();
		for (Book book : BOOKS) {
			if(book.getName().equals(name)){
				list.add(book);
			}
		}
		return list;
	}
	
	public ArrayList<Book> getAllByAuthor(String author){
		ArrayList<Book> list = new ArrayList<>();
		for (Book book : BOOKS) {
			if(book.getAuthor().equals(author)){
				list.add(book);
			}
		}
		return list;
	}
	

	

}
