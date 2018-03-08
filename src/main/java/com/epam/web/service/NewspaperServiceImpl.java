package com.epam.web.service;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.epam.web.util.ResponseHandler;

@Path("/service/news")
public class NewspaperServiceImpl implements NewspaperService {
	private ResponseHandler responseHandler = new ResponseHandler();

	@Override
	public Response getAllNews() {
		return responseHandler.getAllNews();
	}

	@Override
	public Response getNewsById(Integer id) {
		return responseHandler.getNewsById(id);
	}

	@Override
	public Response getNewsByParameters(String title, String category) {
		return responseHandler.getNewsByParameters(title,category);
	}

	@Override
	public Response addOrUpdateNews(Integer id, String title, String category, String description, String link) {
		return responseHandler.addOrUpdateNews(id,title,category,description,link);
	}

	@Override
	public Response deleteNews(Integer id) {
		return responseHandler.deleteNews(id);
	}


}
