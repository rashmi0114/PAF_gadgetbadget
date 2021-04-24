package com.GadgetBadget.resources;

//import jakarta.annotation.security.RolesAllowed;


import java.sql.SQLException;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.GadgetBadget.service.user.UserService;
import com.GadgetBadget.util.User;

import com.google.gson.*;


@Path("/Orders")
public class UserResource {
	User order = new User();

	@RolesAllowed({"admin","buyer"})
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String readCart() {
		UserService order = new UserService();

		String output = order.readCart();
		return output;

	}
	

	@RolesAllowed({"buyer"})
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCart(String orderData) {
		// Convert the input string to a JSON object
		JsonObject OrderObj = new JsonParser().parse(orderData).getAsJsonObject();
		// Read the values from the JSON object

		String orderId = OrderObj.get("orderId").getAsString();
		String buyerName = OrderObj.get("buyerName").getAsString();
		String address = OrderObj.get("address").getAsString();
		String NIC = OrderObj.get("NIC").getAsString();
		String softwareName = OrderObj.get("softwareName").getAsString();
		Integer size = OrderObj.get("size").getAsInt();
		Integer version = OrderObj.get("version").getAsInt();
		Float cost = OrderObj.get("cost").getAsFloat();
		String date = OrderObj.get("date").getAsString();
		
		
		UserService orderobject1 = new UserService();
		order.setOrderId(orderId);
		order.setBuyerName(buyerName);
		order.setAddress(address);
		order.setNIC(NIC);
		order.setSoftwareName(softwareName);
		order.setSize(size);
		order.setVersion(version);
		order.setCost(cost);
		order.setDate(date);

		String output = orderobject1.insertCart(order);
		return output;
	}
	//@RolesAllowed({""})
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCart(String orderData) throws SQLException {
		// Convert the input string to a JSON object
		JsonObject OrderObj = new JsonParser().parse(orderData).getAsJsonObject();
		// Read the values from the JSON object

		String orderId = OrderObj.get("orderId").getAsString();
		String buyerName = OrderObj.get("buyerName").getAsString();
		String address = OrderObj.get("address").getAsString();
		String NIC = OrderObj.get("NIC").getAsString();
		String softwareName = OrderObj.get("softwareName").getAsString();
		Integer size = OrderObj.get("size").getAsInt();
		Integer version = OrderObj.get("version").getAsInt();
		Float cost = OrderObj.get("cost").getAsFloat();
		String date = OrderObj.get("date").getAsString();
		

		UserService orderobject2 = new UserService();
		
		order.setOrderId(orderId);
		order.setBuyerName(buyerName);
		order.setAddress(address);
		order.setNIC(NIC);
		order.setSoftwareName(softwareName);
		order.setSize(size);
		order.setVersion(version);
		order.setCost(cost);
		order.setDate(date);

		String output = orderobject2.updateCart(order);
		return output;
	}
	//@RolesAllowed({""})
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCart(String orderData) {
		JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject();

		// Read the value from the element <orderId>
		String orderId = orderObject.get("orderId").getAsString();

		UserService orderobject3 = new UserService();
		order.setOrderId(orderId);
		String output = orderobject3.deleteCart(order);
		return output;
	}
	
	@RolesAllowed({""})
	@GET
	@Path("/view")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String viewCart() {
		UserService BuyObj = new UserService();

		
		
		
		String output = BuyObj.viewCart();
		return output;
	}

}