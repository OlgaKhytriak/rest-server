package com.epam.web.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.epam.dao.NewsPaperDao;
import com.epam.model.NewsPaperWSException;
import com.epam.model.SingleNews;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import static com.epam.web.util.Constants.*;

public class ResponseHandler {

    private Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private NewsPaperDao DAO = new NewsPaperDao();



    public Response getAllNews() {
        return Response.status(200).entity(GSON.toJson(DAO.getAll())).build();
    }

    public Response getBookById(Integer id) {
        if (id < 0) {
            throw new NewsPaperWSException(INCORRECT_INPUT_VALUE + id, Status.BAD_REQUEST);
        }
        SingleNews result = DAO.get(id);

        if (result == null) {
            throw new NewsPaperWSException(NEWS_BY_ID_NOT_FOUND, Status.NO_CONTENT);
        }
        return Response.status(200).entity(GSON.toJson(DAO.get(id))).build();
    }

    public Response getBooksByParam(String name, String author) {
        List<SingleNews> result = new ArrayList<>();
        if (name == null && author == null) {
            throw new NewsPaperWSException(NO_INPUT_PARAMETERS + "[name:" + name + ",author:" + author + "]",
                    Status.BAD_REQUEST);
        }
        if (name != null && author == null) {
            result = DAO.getAllByName(name);
        } else {
            if (name == null && author != null) {
                result = DAO.getAllByAuthor(author);
            } else {
                if (name != null && author != null) {
                    result = DAO.getAllByName(name);
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
            throw new NewsPaperWSException(BOOK_BY_NAME_OR_AUTHOR_NOT_FOUND
                    + "[name:" + name + ",author:" + author + "]", Status.NO_CONTENT);
        }

        return Response.status(200).entity(GSON.toJson(result)).build();
    }


    public Response addOrUpdateBook(Integer id, String name, String author, String genre) {
        if (id == null || name == null || author == null || genre == null || id < 0) {
            throw new NewsPaperWSException(INCORRECT_INPUT_VALUE
                    + "[id:" + id + ",name:" + name + ",author:" + author + ",genre:" + genre + "]", Status.BAD_REQUEST);
        }

        //Book newBook = new Book(name, author, genre, id); //переписати
        SingleNews newBook = new SingleNews();
        JsonObject result = new JsonObject();

        SingleNews oldBook = DAO.get(id);
        if (oldBook != null) {
            DAO.update(oldBook, newBook);
            result.addProperty("Message", NEWS_WAS_UPDATED);
        } else {
            DAO.add(newBook);
            result.addProperty("Message", NEWS_WAS_ADDED);
        }
        return Response.status(200).entity(result.toString()).build();
    }


    public Response deleteBook(Integer id) {
        if (id < 0) {
            throw new NewsPaperWSException(INCORRECT_INPUT_VALUE + id, Status.BAD_REQUEST);
        }

        JsonObject result = new JsonObject();
        if (DAO.get(id) == null) {
            throw new NewsPaperWSException(NEWS_BY_ID_NOT_FOUND, Status.NO_CONTENT);
        } else {
            DAO.delete(id);
            result.addProperty("Message", NEWS_WAS_DELETED);
        }
        return Response.status(200).entity(result.toString()).build();
    }


}
