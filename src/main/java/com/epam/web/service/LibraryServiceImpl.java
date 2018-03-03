package com.epam.web.service;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.epam.web.service.LibraryService;
import com.epam.web.util.ResponseHandler;

@Path("/service/books")
public class LibraryServiceImpl implements LibraryService{
	private ResponseHandler responseHandler = new ResponseHandler();
	
	
	@Override
	public Response getAllBooks() {
		return responseHandler.getAllNews();
	}

	@Override
	public Response getBookById(Integer id) {
		return responseHandler.getBookById(id);
	}

	@Override
	public Response getBooksByParams(String name,String author) {
		return responseHandler.getBooksByParam(name,author);
	}

	@Override
	public Response addOrUpdateBook(Integer id, String name, String author, String genre) {
		return responseHandler.addOrUpdateBook(id, name, author, genre);
	}

	@Override
	public Response deleteBook(Integer id) {
		return responseHandler.deleteBook(id);
	}

	
 
	
	
	

}
