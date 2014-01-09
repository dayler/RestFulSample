/**
 * 
 */
package com.dayler.sample.web.restful;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.dayler.sample.web.dao.TodoDao;
import com.dayler.sample.web.domain.Todo;

/**
 * @author asalazar
 *
 */
@Path("/todos")
public class TodosResource {

    @Context
    private UriInfo uriInfo;

    @Context
    private Request request;

    /**
     * @return List of Todos to the browser.
     */
    @GET
    @Produces({MediaType.TEXT_XML})
    public List<Todo> getTodosBrowser() {
        List<Todo> todos = new ArrayList<Todo>();
        todos.addAll(TodoDao.instance.getModel().values());

        return todos;
    }

    /**
     * 
     * @return List of todos to the application.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Todo> getTodos() {
        List<Todo>todos = new ArrayList<Todo>();
        todos.addAll(TodoDao.instance.getModel().values());

        return todos;
    }

    @GET
    @Path("count")
    @Produces({MediaType.TEXT_PLAIN})
    public String getCount() {
        int count = TodoDao.instance.getModel().size();

        return String.valueOf(count);
    }

}
