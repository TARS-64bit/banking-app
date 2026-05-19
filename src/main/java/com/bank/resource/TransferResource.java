package com.bank.resource;

import com.bank.dto.TransactionEvent;
import com.bank.dto.TransferRequest;
import com.bank.dto.TransferResponse;
import com.bank.service.TransactionProducer;
import com.bank.service.TransferService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/transfer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransferResource {

    @Inject
    TransferService transferService;

    @Inject
    TransactionProducer transactionProducer;

    @POST
    public Response transfer(@Valid TransferRequest request){
        TransferResponse response = transferService.transfer(request);

        return Response.ok(response).build();
    }

    @POST
    @Path("/mq")
    public Response transferMq(@Valid TransactionEvent event){
        transactionProducer.publish(event);
        return Response.ok("Published").build();
    }
}
