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
import model.Item;

@Path("/Items")
public class ItemService {
	Item itemObj = new Item();

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
	public String insertItem(@FormParam("sellerName") String itemCode, @FormParam("sellerCategory") String itemName,
			@FormParam("sellerPhone") String itemPrice, @FormParam("sellerEmail") String itemDesc) {
		String output = itemObj.insertItem(itemCode, itemName, itemPrice, itemDesc);
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
		String sellerId = itemObject.get("sellerId").getAsString();
		String sellerName = itemObject.get("sellerName").getAsString();
		String sellerCategory = itemObject.get("sellerCategory").getAsString();
		String sellerPhone = itemObject.get("sellerPhone").getAsString();
		String sellerEmail = itemObject.get("sellerEmail").getAsString();
		String output = itemObj.updateItem(sellerId, sellerName, sellerCategory, sellerPhone, sellerEmail);
		return output;
	}

	// ete method is given below.
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData) {//oshan-codelab
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String sellerId = doc.select("sellerId").text();
		String output = itemObj.deleteItem(sellerId);
		return output;
	}

}