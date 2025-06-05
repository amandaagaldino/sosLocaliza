package fiap.tds.resource;

import fiap.tds.dto.SmsRequest;
import fiap.tds.entity.SmsMessage;
import fiap.tds.repository.SmsRepository;
import fiap.tds.service.SmsSender;
import jakarta.ws.rs.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/sms")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SmsSenderResource {

    private final SmsSender smsSender;
    private final SmsRepository smsRepository;

    @Inject
    public SmsSenderResource(SmsSender smsSender) {
        this.smsSender = smsSender;
        this.smsRepository = new SmsRepository();
    }

    @POST
    public Response sendSms( SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
        return Response.ok("SMS enviado com sucesso").build();

    }
// Estamos usando a versão de teste, apenas o número cadastrado a baixo funcionará
//    {
//        "sender": "Maria Silva",
//            "ddd": 11,
//            "phoneNumber": "966696589",
//            "message": "Ajuda!"
//    }

    @GET
    @Path("/getAll")
    public Response getAllSms(){
        List<SmsMessage> smsMessages = smsRepository.getAll();
        return Response.ok(smsMessages).build();
    }

}
