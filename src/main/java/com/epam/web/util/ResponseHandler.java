package com.epam.web.util;

import java.util.ArrayList;
import java.util.Iterator;
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

    //---------------------------------------------------
    public Response addOrUpdateBook(Integer id, String name, String author, String genre) {
        if (id == null || name == null || author == null || genre == null || id < 0) {
            throw new WSException(INCORRECT_INPUT_DATA
                    + "[id:" + id + ",name:" + name + ",author:" + author + ",genre:" + genre + "]", Status.BAD_REQUEST);
        }

        //Book newBook = new Book(name, author, genre, id); //переписати
        SingleNews newBook = new SingleNews();
        JsonObject result = new JsonObject();

        SingleNews oldBook = newsPaperDao.getById(id);
        if (oldBook != null) {
            newsPaperDao.update(oldBook, newBook);
            result.addProperty("Message", NEWS_WAS_UPDATED);
        } else {
            newsPaperDao.add(newBook);
            result.addProperty("Message", NEWS_WAS_ADDED);
        }
        return Response.status(200).entity(result.toString()).build();
    }


    public Response deleteBook(Integer id) {
        ifIdIncorrect(id);
        JsonObject result = new JsonObject();
        if (newsPaperDao.getById(id) == null) {
            throw new WSException(NEWS_IS_NOT_FOUND, Status.NO_CONTENT);
        } else {
            newsPaperDao.delete(id);
            result.addProperty("Message", NEWS_WAS_DELETED);
        }
        return Response.status(200).entity(result.toString()).build();
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
            message = String.format("%s %s = %s", NEWS_IS_NOT_FOUND, args[0], args[1]);
        } else if (4 == args.length) {
            message = String.format("%s %s = %s; %s = %s", NEWS_IS_NOT_FOUND, args[0], args[1], args[2], args[3]);
        }
        throw new WSException(message, Status.NO_CONTENT);
    }


}
