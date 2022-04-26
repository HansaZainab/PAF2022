package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Billing;

@Path("/Billing")
public class BillingAPI {
	Billing bill = new Billing();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBilling() {
		return bill.readBilling();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBilling(
	 @FormParam("b_accNo") String billAccNo,		
	 @FormParam("b_date") String billDate,
	 @FormParam("b_unit") String billUnit,
	 @FormParam("b_unitPrice") String billUnitPrice,
	 @FormParam("b_total") String billTotal)
	{
	 String output = bill.insertBilling(billAccNo, billDate, billUnit, billUnitPrice, billTotal);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBilling(String billData)
	{
		
	//Convert the input string to a JSON object
		
	 JsonObject bill_Obj = new JsonParser().parse(billData).getAsJsonObject();
	 
	 
	//Read the values from the JSON object
	 String ID = bill_Obj.get("b_ID").getAsString();
	 String AccNo = bill_Obj.get("b_accNo").getAsString();
	 String Date = bill_Obj.get("b_date").getAsString();
	 String Unit = bill_Obj.get("b_unit").getAsString();
	 String UnitPrice = bill_Obj.get("b_unitPrice").getAsString();
	 String Total = bill_Obj.get("b_total").getAsString();
	 String output = bill.updateBilling(ID, AccNo, Date, Unit, UnitPrice, Total);
	return output;
	} 
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBilling(String billData)
	{
		
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String ID = doc.select("b_ID").text();
	 String output = bill.deleteBilling(ID);
	return output;
	}
}
