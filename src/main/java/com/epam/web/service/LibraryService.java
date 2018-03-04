package com.epam.web.service;

import com.epam.model.SingleNews;

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
import java.util.List;

public interface LibraryService {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllNews();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewsById(@PathParam("id") Integer id);

    @GET
    @Path("/params")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewsByParameters(@QueryParam("title") String title,
                                        @QueryParam("category") String category);

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addOrUpdateNews(@PathParam("id") Integer id,
                                    @FormParam("title") String title,
                                    @FormParam("category") String category,
                                    @FormParam("description") String description,
                                    @FormParam("link") String link);
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNews(@PathParam("id") Integer id);

}
