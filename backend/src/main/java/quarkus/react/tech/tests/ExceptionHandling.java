package quarkus.react.tech.tests;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandling implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException exception) {
        if (exception instanceof MyExceptions.AlreadyExistsException) {
            return Response.status(409).entity(exception.getMessage()).build();
        } else {
            return Response.status(500).entity(exception.getMessage()).build();
        }
    }
}
