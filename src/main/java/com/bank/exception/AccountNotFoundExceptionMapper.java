package com.bank.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AccountNotFoundExceptionMapper
        implements ExceptionMapper<AccountNotFoundException> {

    @Override
    public Response toResponse(AccountNotFoundException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("AC404", e.getMessage()))
                .build();
    }
}
