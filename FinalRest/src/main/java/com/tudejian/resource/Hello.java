package com.tudejian.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;

/**
 * If you deploy with tomcat in Eclipse:
 * URL to access REST API:
 * http://localhost:8080/FinalRest/service/hellotudejian
 * URL to acccess swagger.json:
 * http://localhost:8080/FinalRest/service/swagger.json
 * 
 * If you deploy using Jetty
 * Command: java -jar ./target/dependency/jetty-runner.jar ./target/FinalRest-0.0.1-SNAPSHOT.war
 * URL to access REST API:
 * http://localhost:8080/service/hellotudejian
 * URL to acccess swagger.json:
 * http://localhost:8080/service/swagger.json
 */

@Api(value = "Hello")
@Path("/hellotudejian")
public class Hello {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey TEXT_PLAIN hahaha";
	}

	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey TEXT_XML hahaha" + "</hello>";
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Jersey tudejian TEXT_HTML" + "</title>" + "<body><h1>"
				+ "Hello Jersey TEXT_HTML hahaha" + "</body></h1>" + "</html> ";
	}

}
