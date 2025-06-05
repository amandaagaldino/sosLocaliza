package fiap.tds.resource;

import fiap.tds.dto.EventoDto;
import fiap.tds.service.EventoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/eventos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventoResource {

    private final EventoService eventoService = new EventoService();

    @POST
    @Path("/add")
    public void addEvento(EventoDto dto) {
        eventoService.addEvento(dto);
    }
//  Para testar
//    {
//        "nomeEvento": "Teste",
//            "descricaoEvento": "Descrição do evento teste",
//            "causas": "Causa teste",
//            "alertas": "Alerta teste",
//            "acoesAntes": "Ações antes teste",
//            "acoesDurante": "Ações durante teste",
//            "acoesDepois": "Ações depois teste"
//    }

    @GET
    @Path("/getAll")
    public List<EventoDto> getAllEventos() {
        return eventoService.getAllEventos();
    }

    //ID=4 - Inundações   ID=5 - Tempestades, raios e granizos
    @GET
    @Path("getById/{id}")
    public Response getEventoById(@PathParam("id") int id) {
        Optional<EventoDto> evento = eventoService.getEventoById(id);
        return evento.map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }


    @PUT
    @Path("update/{id}")
    public void updateEvento(@PathParam("id") int id, EventoDto dto) {
        eventoService.updateEvento(id, dto);
    }
//    {
//        "nomeEvento": "Evento Atualizado",
//            "descricaoEvento": "Descrição atualizada",
//            "causas": "Causas atualizadas",
//            "alertas": "Alertas atualizados",
//            "acoesAntes": "Ações antes atualizadas",
//            "acoesDurante": "Ações durante atualizadas",
//            "acoesDepois": "Ações depois atualizadas"
//    }

    //Foi criado um evento teste para testar o delete (id=7)
    @DELETE
    @Path("delete/{id}")
    public void deleteEvento(@PathParam("id") int id) {
        eventoService.deleteEvento(id);
    }
}
