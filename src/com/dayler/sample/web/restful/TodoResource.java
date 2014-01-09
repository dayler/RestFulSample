/**
 * 
 */
package com.dayler.sample.web.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.dayler.sample.web.dao.TodoDao;
import com.dayler.sample.web.domain.Todo;

/**
 * JAXB Sample resource for Restful.
 * 
 * @author asalazar
 *
 */
// @Path("/todo")
public class TodoResource {

    @Context
    private UriInfo uriInfo;

    @Context
    private Request request;

    private String id;

    public TodoResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    /**
     * Get todo for application
     * 
     * @return
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Todo getTodo() {
        Todo todo = TodoDao.instance.getModel().get(id);

        if (todo == null) {
            throw new RuntimeException("Get: Todo with " + id + " not found");
        }

        return todo;
    }

    @GET
    @Produces({MediaType.TEXT_XML})
    public Todo getTodoHtml() {
        Todo todo = TodoDao.instance.getModel().get(id);

        if (todo == null) {
            throw new RuntimeException("Get: Todo with " + id + " not found");
        }

        return todo;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putTodo(JAXBElement<Todo> todoJAXB) {
        Todo todo = todoJAXB.getValue();
        return putAndGetResponse(todo);
    }

    @DELETE
    public void deleteTodo() {
        Todo todo = TodoDao.instance.getModel().remove(id);

        if (todo == null) {
            throw new RuntimeException("Delete: Todo with " + id + " not found.");
        }
    }

    private Response putAndGetResponse(Todo todo) {
        Response res;

        if (TodoDao.instance.getModel().containsKey(id)) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }

       TodoDao.instance.getModel().put(todo.getId(), todo);

        return res;
    }

//    /**
//     * Json and xml serialization
//     * 
//     * @return
//     */
//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Todo getTodoXmlAndJson() {
//        Todo todo = new Todo();
//        todo.setSummary("Summary for xml and json serialization sample");
//        todo.setDescription("xml and json todo object sample");
//
//        return todo;
//    }
//
//    /**
//     * For integration with browser.
//     * 
//     * @return
//     */
//    @GET
//    @Produces(MediaType.TEXT_XML)
//    public Todo getTodoHtml() {
//        Todo todo = new Todo();
//        todo.setSummary("Summary for html serialization sample");
//        todo.setDescription("html todo object sample");
//
//        return todo;
//    }
}
