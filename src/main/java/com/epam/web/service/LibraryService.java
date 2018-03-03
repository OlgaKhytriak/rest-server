package com.epam.web.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface LibraryService {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllBooks();

	@GET
	@Path("/params")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooksByParams(@QueryParam("name") String name,
			@QueryParam("author") String author);
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookById(@PathParam("id") Integer id);
	
	
	@POST
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addOrUpdateBook(@PathParam("id") Integer id,
			@FormParam("name") String name,
			@FormParam("author") String author,
			@FormParam("genre") String genre);
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBook(@PathParam("id") Integer id);

}
