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
        ifNotFoundById(result, id);
        return Response.status(200).entity(gson.toJson(result)).build();
    }

    public Response getBooksByParam(String name, String author) {
        List<SingleNews> result = new ArrayList<>();
        if (name == null && author == null) {
            throw new WSException(NO_INPUT_PARAMETERS + "[name:" + name + ",author:" + author + "]",
                    Status.BAD_REQUEST);
        }
        if (name != null && author == null) {
            result = newsPaperDao.getAllByName(name);
        } else {
            if (name == null && author != null) {
                result = newsPaperDao.getAllByAuthor(author);
            } else {
                if (name != null && author != null) {
                    result = newsPaperDao.getAllByName(name);
                    Iterator<SingleNews> iter = result.iterator();
                    while (iter.hasNext()) {
                        if (!iter.next().getTitle().equals(author)) {
                            iter.remove();
                        }
                    }
                }
            }
        }

        if (result.size() < 1) {
            throw new WSException(BOOK_BY_NAME_OR_AUTHOR_NOT_FOUND
                    + "[name:" + name + ",author:" + author + "]", Status.NO_CONTENT);
        }

        return Response.status(200).entity(gson.toJson(result)).build();
    }


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

    private boolean ifIdIncorrect(Integer id) {
        boolean isIncorrect = false;
        if (id < 0) {
            isIncorrect = true;
            String message = String.format("%s id = %s", INCORRECT_INPUT_DATA, id);
            throw new WSException(message, Status.BAD_REQUEST);
        }
        return isIncorrect;
    }

    private boolean ifNotFoundById(SingleNews result, Integer id) {
        boolean isNull = (result == null);
        if (isNull) {
            String message = String.format("%s id = %s", NEWS_IS_NOT_FOUND, id);
            throw new WSException(message, Status.NO_CONTENT);
        }
        return isNull;
    }
}
