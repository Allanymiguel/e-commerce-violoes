package br.unitins.ecommerce.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {
        int status = exception.getResponse().getStatus();
        String reason = Response.Status.fromStatusCode(status) != null
                ? Response.Status.fromStatusCode(status).getReasonPhrase()
                : "Error";

        Problem problem = new Problem(
                "https://br.unitins.ecommerce/" + reason.toLowerCase().replace(' ', '-'),
                reason,
                status,
                exception.getMessage()
        );

        return Response.status(status)
                .entity(problem)
                .build();
    }
}
