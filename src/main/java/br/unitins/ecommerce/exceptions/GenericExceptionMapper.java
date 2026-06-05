package br.unitins.ecommerce.exceptions;

import io.quarkus.logging.Log;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse();
        }

        Log.errorf(exception, "Erro nao tratado: %s", exception.getMessage());

        Problem problem = new Problem(
                "https://br.unitins.ecommerce/internal-server-error",
                "Internal Server Error",
                500,
                "Erro interno. Consulte o log do servidor."
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(problem)
                .build();
    }
}
