package com;

import javax.ws.rs.core.MediaType;
//For REST Service
import javax.ws.rs.*;
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
//package com;
import model.product;

@Path("/product")
public class pafAssignment {
	product itemObj = new product();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readItems();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("Pname") String Pname, @FormParam("Pdesc") String Pdesc,
			@FormParam("Pprice") String Pprice, @FormParam("Pcategory") String Pcategory) {
		String output = itemObj.insertItem(Pname, Pdesc, Pprice, Pcategory);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData) {
		// Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		// Read the values from the JSON object
		String Pid = itemObject.get("Pid").getAsString();
		String Pname = itemObject.get("Pname").getAsString();
		String Pdesc = itemObject.get("Pdesc").getAsString();
		String Pcategory = itemObject.get("Pcategory").getAsString();
		String Pprice = itemObject.get("Pprice").getAsString();
		String output = itemObj.updateItem(Pid, Pname, Pdesc, Pcategory, Pprice);
		return output;
	}

	// ete method is given below.
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String Pid = doc.select("Pid").text();
		String output = itemObj.deleteItem(Pid);
		return output;}
	}
