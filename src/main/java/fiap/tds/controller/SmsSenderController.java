package fiap.tds.controller;

import fiap.tds.dto.SmsRequest;
import fiap.tds.service.SmsSender;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SmsSenderController {

    private final SmsSender smsSender;

    @Inject
    public SmsSenderController(SmsSender smsSender) {
        this.smsSender = smsSender;
    }

    @POST
    public Response sendSms( SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
        return Response.ok("SMS enviado com sucesso").build();

    }

}
