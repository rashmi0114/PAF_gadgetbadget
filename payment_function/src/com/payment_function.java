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
import model.payment;

@Path("/payment")
public class payment_function {
	payment itemObj = new payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return itemObj.readpayment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("userID") String userID, @FormParam("cardname") String cardname,
			@FormParam("cardnumber") String cardnumber, @FormParam("expire_date") String expire_date,@FormParam("cvv") String cvv) {
		String output = itemObj.insertpayment(userID, cardname, cardnumber, expire_date,cvv);
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
		String paymentID = itemObject.get("paymentID").getAsString();
		String userID = itemObject.get("userID").getAsString();
		String cardname = itemObject.get("cardname").getAsString();
		String cardnumber = itemObject.get("cardnumber").getAsString();
		String expire_date = itemObject.get("expire_date").getAsString();
		String cvv = itemObject.get("cvv").getAsString();
		String output = itemObj.updatepayment(paymentID, userID, cardname, cardnumber, expire_date,cvv);
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
		String paymentID = doc.select("paymentID").text();
		String output = itemObj.deletepayment(paymentID);
		return output;
	}




}
