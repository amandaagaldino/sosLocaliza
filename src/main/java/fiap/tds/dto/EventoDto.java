package fiap.tds.dto;

public record EventoDto(
        int idEvento,
        String nomeEvento,
        String descricaoEvento,
        String causas,
        String alertas,
        String acoesAntes,
        String acoesDurante,
        String acoesDepois
) {
}
