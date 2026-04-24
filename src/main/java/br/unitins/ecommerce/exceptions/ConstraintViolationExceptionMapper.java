package br.unitins.ecommerce.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Problem problem = new Problem(
                "https://br.unitins.ecommerce/bad-request",
                "Bad Request",
                400,
                "One or more validation errors occurred."
        );

        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String field = extractField(violation.getPropertyPath().toString());
            String message = violation.getMessage();
            problem.addViolation(field, message);
        }

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(problem)
                .build();
    }

    private String extractField(String propertyPath) {
        if (propertyPath == null) {
            return null;
        }
        int lastDotIndex = propertyPath.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return propertyPath.substring(lastDotIndex + 1);
        }
        return propertyPath;
    }
}
