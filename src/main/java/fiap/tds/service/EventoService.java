package fiap.tds.service;

import fiap.tds.dto.EventoDto;
import fiap.tds.entity.Evento;
import fiap.tds.exception.EventoException;
import fiap.tds.repository.EventoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class EventoService {

    private final EventoRepository eventoRepository = new EventoRepository();

    public void addEvento(EventoDto dto){
        validarEventoDto(dto);

        Evento evento = new Evento();
        evento.setIdEvento(dto.idEvento());
        evento.setNomeEvento(dto.nomeEvento());
        evento.setDescricaoEvento(dto.descricaoEvento());
        evento.setCausas(dto.causas());
        evento.setAlertas(dto.alertas());
        evento.setAcoesAntes(dto.acoesAntes());
        evento.setAcoesDurante(dto.acoesDurante());
        evento.setAcoesDepois(dto.acoesDepois());

        eventoRepository.add(evento);

    }

    private void validarEventoDto(EventoDto dto) {
    if (dto.nomeEvento() == null || dto.nomeEvento().isBlank())
        throw new IllegalArgumentException("Nome do evento é obrigatório.");
    if (dto.descricaoEvento() == null || dto.descricaoEvento().isBlank())
        throw new IllegalArgumentException("Descrição do evento é obrigatória.");
    if (dto.causas() == null || dto.causas().isBlank())
        throw new IllegalArgumentException("Causas são obrigatórias.");
    if (dto.alertas() == null || dto.alertas().isBlank())
        throw new IllegalArgumentException("Alertas são obrigatórios.");
    if (dto.acoesAntes() == null || dto.acoesAntes().isBlank())
        throw new IllegalArgumentException("Ações antes do evento são obrigatórias.");
    if (dto.acoesDurante() == null || dto.acoesDurante().isBlank())
        throw new IllegalArgumentException("Ações durante o evento são obrigatórias.");
    if (dto.acoesDepois() == null || dto.acoesDepois().isBlank())
        throw new IllegalArgumentException("Ações depois do evento são obrigatórias.");
    }


    public Optional<EventoDto> getEventoById(int id) {
        Optional<Evento> evento = eventoRepository.getById(id);
        if(evento.isEmpty()) {
            throw new EventoException("Evento com id " + id + " não encontrado.");
        }
        return evento.map(this::toDto);
    }

    public List<EventoDto> getAllEventos() {
        try {
            return eventoRepository.getAll().stream()
                    .map(this::toDto)
                    .collect(Collectors.toList());
        }
        catch (Exception e){
            throw new RuntimeException("Erro ao buscar todos os eventos.");
        }
    }

    public void updateEvento(int id, EventoDto dto) {
        Evento evento = new Evento();
        evento.setIdEvento(id);
        evento.setNomeEvento(dto.nomeEvento());
        evento.setDescricaoEvento(dto.descricaoEvento());
        evento.setCausas(dto.causas());
        evento.setAlertas(dto.alertas());
        evento.setAcoesAntes(dto.acoesAntes());
        evento.setAcoesDurante(dto.acoesDurante());
        evento.setAcoesDepois(dto.acoesDepois());

        boolean atualizado = eventoRepository.atualizar(id, evento);
        if (!atualizado) {
            throw new EventoException("Evento com id " + id + " não encontrado para atualizar.");
        }
    }

    public void deleteEvento(int id) {
        boolean deleted = eventoRepository.deleteById(id);
        if (!deleted) {
            throw new EventoException("Evento com id " + id + " não encontrado para deletar.");
        }
    }

    // Conversão DTO <-> Entidade
    private EventoDto toDto(Evento evento) {
        return new EventoDto(
                evento.getIdEvento(),
                evento.getNomeEvento(),
                evento.getDescricaoEvento(),
                evento.getCausas(),
                evento.getAlertas(),
                evento.getAcoesAntes(),
                evento.getAcoesDurante(),
                evento.getAcoesDepois()
        );
    }

}
