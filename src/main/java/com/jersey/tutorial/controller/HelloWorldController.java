package com.jersey.tutorial.controller;

import com.jersey.tutorial.model.CustomParam;
import com.jersey.tutorial.model.HelloWorldMsg;
import com.jersey.tutorial.model.TestBeanParam;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

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
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes tells the MIME type that this endpoint uses
    @Path("json")
    public HelloWorldMsg getMsgObject() {
        HelloWorldMsg msg = new HelloWorldMsg();
        return msg;
    }

    @GET
    @Path("/withParams")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldMsg getMsgWithParams(
            @DefaultValue("2") @QueryParam("numParam") Integer numberParam,
            @QueryParam ("word") CustomParam inString,
            //CustomParam has a constructor that accepts a single string, so this will work
            //alternatively you can use a static method valueOf or fromString that accepts a single string arg
            @Context HttpHeaders hh
    ) {
        HelloWorldMsg msg = new HelloWorldMsg();
        msg.msg="NumParam: " + numberParam + " firstChar: " + inString.firstLetter +
                " context Media types: " + hh.getAcceptableMediaTypes();
        return msg;
    }

    @GET
    @Path("/{id}/beanParams")
    @Produces(MediaType.APPLICATION_JSON)
    public TestBeanParam getWithBeanParams(
            @BeanParam TestBeanParam beanParam) {
        return beanParam;
    }
}
