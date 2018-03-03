package com.epam.web.service.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.epam.web.service.LibraryService;
import com.epam.web.util.ResponseHandler;

@Path("/service/books")
public class LibraryServiceImpl implements LibraryService{
	
	
	@Override
	public Response getAllBooks() {
		return ResponseHandler.getAllBooks();
	}

	@Override
	public Response getBookById(Integer id) {
		return ResponseHandler.getBookById(id);
	}

	@Override
	public Response getBooksByParams(String name,String author) {
		return ResponseHandler.getBooksByParam(name,author);
	}

	@Override
	public Response addOrUpdateBook(Integer id, String name, String author, String genre) {
		return ResponseHandler.addOrUpdateBook(id, name, author, genre);
	}

	@Override
	public Response deleteBook(Integer id) {
		return ResponseHandler.deleteBook(id);
	}

	
 
	
	
	

}
