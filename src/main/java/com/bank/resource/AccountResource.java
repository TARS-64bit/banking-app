package com.bank.resource;

import com.bank.dto.AccountResponse;
import com.bank.dto.AddAccountRequest;
import com.bank.dto.AccountDepositRequest;
import com.bank.service.AccountService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService accountService;

    @POST
    public Response addAccount(@Valid AddAccountRequest request){
        AccountResponse response = accountService.addAccount(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    @Path("/{id}")
    public Response getAccount(@PathParam("id") Long accountId){
        AccountResponse response = accountService.getAccount(accountId);
        return  Response.ok(response).build();
    }

    @POST
    @Path("/{id}/deposit")
    public Response deposit(@PathParam("id") Long id, @Valid AccountDepositRequest request){
        AccountResponse response = accountService.deposit(id, request);

        return Response.ok(response).build();
    }
}
