package br.ufba.dcc.wiser.fot.manager.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookService {

	protected final Logger log = LoggerFactory.getLogger(BookService.class);

	@POST
	@Path("/getbook/{name}")
	@Produces("application/json")
	//@Consumes({ "application/xml", "application/json", "application/x-www-form-urlencoded" })
	public Response getBucket(@PathParam("name") String name) {
		log.debug("name : " + name);
		BookVO bookVO = null;
		try {
			bookVO = HashDB.getBook(URLDecoder.decode(name, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}

		if (bookVO == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(bookVO).build();
		}
	}

	@POST
	@Path("/addbook")
	@Produces("application/json")
	//@Produces({ "application/xml", "application/json" })
	//@Consumes({ "application/xml", "application/json", "application/x-www-form-urlencoded" })
	public Response addBook(@FormParam("name") String bookName, @FormParam("author") String author) {
		log.debug("inside addBook");
		BookVO bookVO = new BookVO();
		bookVO.setBookName(bookName);
		bookVO.setAuthor(author);
		HashDB.insertBook(bookVO);
		System.out.println(">>>>>>>>Ok!");
		if (HashDB.getBook(bookName) == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(bookVO).build();
		}
	}
	
	@POST
	@Path("/addgateway")
	@Produces("application/json")
	//@Produces({ "application/xml", "application/json" })
	//@Consumes({ "application/xml", "application/json", "application/x-www-form-urlencoded" })
	public Response addGateway(String value) {
		log.debug("inside addGateway");

		System.out.println(">>>>>>>> " + value);
		if (HashDB.getBook(value) == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return Response.ok(value).build();
		}

	}

}
