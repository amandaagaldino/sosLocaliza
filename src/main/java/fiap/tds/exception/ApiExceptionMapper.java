package fiap.tds.exception;

import com.twilio.exception.ApiException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class ApiExceptionMapper implements ExceptionMapper<ApiException> {

    @Override
    public Response toResponse(ApiException e) {
        ExceptionDetails details = new ExceptionDetails(
                e.getMessage(),
                LocalDateTime.now(),
                Response.Status.BAD_REQUEST.getStatusCode(),
                e.getClass().getName()
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(details)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}