package quarkus.react.tech.tests;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;
import quarkus.react.tech.tests.gallery.pictures.PicturesDAO;

@Provider
public class ExceptionHandling implements ExceptionMapper<RuntimeException> {
    Logger logger = Logger.getLogger(ExceptionHandling.class);
    @Override
    public Response toResponse(RuntimeException exception) {
        if (exception instanceof MyExceptions.AlreadyExistsException) {
            return Response.status(409).entity(exception.getMessage()).build();
        } else {
            logger.error(exception.getMessage());
            return Response.status(500).entity(exception.getMessage()).build();
        }
    }
}
