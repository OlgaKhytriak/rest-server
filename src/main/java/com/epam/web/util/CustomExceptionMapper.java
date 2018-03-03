package com.epam.web.util;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.epam.model.LibraryWSException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception>{
	
	 public Response toResponse(Exception e) {
		 Map<String,String> map = new HashMap<>();
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 
		 if(e instanceof LibraryWSException){
			 LibraryWSException libException = (LibraryWSException)e;
			 map.put("Title", "Failure");
			 map.put("Code",libException.getStatus().getStatusCode()
					 +" - "+libException.getStatus());
			 map.put("Message", e.getMessage());
		 }else{
			 map.put("Title", "Failure");
			 map.put("Message", e.getMessage());
		 }
		 
		 return Response.status(200).entity(gson.toJson(map)).build();
	   }
	 
}
