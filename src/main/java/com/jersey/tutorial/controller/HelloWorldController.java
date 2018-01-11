package com.jersey.tutorial.controller;

import com.jersey.tutorial.model.HelloWorldMsg;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

//a path can begin with / or not, it doesn't matter
@Path("/helloworld")
public class HelloWorldController {
    public static final String GREETING_MSG = "Hello World";

    @GET
    @Produces("text/plain")
    public String getHello() {
        return GREETING_MSG;
    }

    //a {} indicates a path variable.  You can also include regular expressions to restrict the legal values of the
    //path param (if it is a string)
    //this returns 404 if special characters other than _ are in the name
    @Path("/{username: [a-zA-Z][a-zA-Z_0-9]*}")
    @GET
    @Produces("text/plain")
    public String echoUsername(@PathParam("username") String userName) {
        return userName;
    }

    @GET
    @Produces("application/json")
    @Path("json")
    public HelloWorldMsg getMsgObject() {
        HelloWorldMsg msg = new HelloWorldMsg();
        return msg;
    }
}
