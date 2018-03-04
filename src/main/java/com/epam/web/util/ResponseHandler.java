package com.epam.web.util;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.epam.dao.NewsPaperDao;
import com.epam.model.SingleNews;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import static com.epam.web.util.Constants.*;

public class ResponseHandler {

    private final Gson gson;
    private final NewsPaperDao newsPaperDao;

    public ResponseHandler() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        newsPaperDao = new NewsPaperDao();
    }

    public Response getAllNews() {
        return Response.status(200).entity(gson.toJson(newsPaperDao.getAll())).build();
    }

    public Response getNewsById(Integer id) {
        ifIdIncorrect(id);
        SingleNews result = newsPaperDao.getById(id);
        if (null == result) {
            ifNotFound("id", id.toString());
        }
        return Response.status(200).entity(gson.toJson(result)).build();
    }

    public Response getNewsByParameters(String title, String category) {
        List<SingleNews> result = null;
        if (title != null && category == null) {
            result = newsPaperDao.getByTitle(title);
        }
        if (title == null && category != null) {
            result = newsPaperDao.getByCategory(category);
        }
        if (title != null && category != null) {
            result = newsPaperDao.getByTitleAndCategory(title, category);
        }
        if (title == null && category == null) {
            ifAllParametersNull(title, category);
        }
        if (result.size() < 1) {
            ifNotFound("title", title.toString(), "category", category.toString());
        }
        return Response.status(200).entity(gson.toJson(result)).build();
    }

    public Response addOrUpdateNews(Integer id, String title, String category, String description, String link) {

        SingleNews newSingleNews =createSingleNews(id,title,category,description,link);
        SingleNews oldSingleNews = newsPaperDao.getById(id);
        JsonObject result = new JsonObject();

        if (oldSingleNews != null) {
            newsPaperDao.update(oldSingleNews, newSingleNews);
            result.addProperty("Message", NEWS_UPDATED);
        } else {
            newsPaperDao.add(newSingleNews);
            result.addProperty("Message", NEWS_ADDED);
        }
        return Response.status(200).entity(result.toString()).build();
    }

    public Response deleteNews(Integer id) {
        ifIdIncorrect(id);
        JsonObject result = new JsonObject();
        if (newsPaperDao.getById(id) == null) {
            ifNotFound("id", id.toString());
        }
        else {
            newsPaperDao.delete(id);
            result.addProperty("Message", NEWS_DELETED);
        }
        return Response.status(200).entity(result.toString()).build();
    }

    private SingleNews createSingleNews(Integer id, String title, String category, String description, String link){
        if (id == null || title == null || category == null || description == null || link==null || id < 0) {
            String message = String.format("%s id = %s; title= %s; category = %s; description = %s; link = %s", INCORRECT_INPUT_DATA, id, title,category,description,link);
            throw new WSException(message, Status.BAD_REQUEST);
        }
        return new SingleNews(id, title, category, description, link);
    }

    private void ifAllParametersNull(String title, String category) {
        String message = String.format("%s title = %s; category = %s", NO_INPUT_PARAMETERS, title, category);
        throw new WSException(message, Status.BAD_REQUEST);
    }

    private boolean ifIdIncorrect(Integer id) {
        boolean isIncorrect = (id <= 0);
        if (isIncorrect) {
            String message = String.format("%s id = %s", INCORRECT_INPUT_DATA, id);
            throw new WSException(message, Status.BAD_REQUEST);
        }
        return isIncorrect;
    }

    private void ifNotFound(String... args) {
        String message = null;
        if (2 == args.length) {
            message = String.format("%s %s = %s", NEWS_NOT_FOUND, args[0], args[1]);
        } else if (4 == args.length) {
            message = String.format("%s %s = %s; %s = %s", NEWS_NOT_FOUND, args[0], args[1], args[2], args[3]);
        }
        throw new WSException(message, Status.NO_CONTENT);
    }


}
