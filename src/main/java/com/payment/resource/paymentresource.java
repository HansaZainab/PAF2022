package com.payment.resource;


import com.payment.model.paymentmodel;
import com.payment.service.paymentservice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/payment")
public class paymentresource {
    paymentservice service = new paymentservice();

    @Path("/insertion")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public paymentmodel addPayment(paymentmodel payment) {
        return service.insertPayment(payment);

    }

    @Path("/retrieve")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<paymentmodel> getPayment() throws SQLException {
        return service.getPayment();

    }

    @Path("/retrieveById/{paymentId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<paymentmodel>getPayment(@PathParam("paymentId") int paymentId) throws SQLException {
        return service.getPaymentById(paymentId);

    }



    @Path("/updatePayment")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public paymentmodel updatePayment(paymentmodel payment) {
        return service.updatePayment(payment);

    }

    @Path("/deleteFundById/{paymentId}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public int deletePayment(@PathParam("paymentId") int paymentId) {
        return service.deletePayment(paymentId);
    }
}
