package fiap.tds.repository;

import fiap.tds.entity.Evento;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventoRepository {

    //Para adicionar se preciso novos dados de eventos
    public void add(Evento object) {
        var query = "Insert into \"T_SOS_EVENTO\"(nome_evento, descricao_evento, causas, alertas, acoes_antes, acoes_durante, acoes_depois) values (?, ?, ?, ?, ?, ?, ?)";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            stmt.setString(1, object.getNomeEvento());
            stmt.setString(2, object.getDescricaoEvento());
            stmt.setString(3, object.getCausas());
            stmt.setString(4, object.getAlertas());
            stmt.setString(5, object.getAcoesAntes());
            stmt.setString(6, object.getAcoesDurante());
            stmt.setString(7, object.getAcoesDepois());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    //Apagar dados
    public boolean deleteById(int id) {
        var query = "DELETE FROM T_SOS_EVENTO WHERE id_evento = ?";
        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Buscar todos os eventos
    public List<Evento> getAll() {
        var eventos = new ArrayList<Evento>();
        var query = "Select * FROM \"T_SOS_EVENTO\"";
        try (var connection = DatabaseConfig.getConnection()){
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();
            while(result.next()){
                var evento = new Evento();
                evento.setIdEvento(result.getInt("id_evento"));
                evento.setNomeEvento(result.getString("nome_evento"));
                evento.setDescricaoEvento(result.getString("descricao_evento"));
                evento.setCausas(result.getString("causas"));
                evento.setAlertas(result.getString("alertas"));
                evento.setAcoesAntes(result.getString("acoes_antes"));
                evento.setAcoesDurante(result.getString("acoes_durante"));
                evento.setAcoesDepois(result.getString("acoes_depois"));

                eventos.add(evento);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return eventos;
    }

    //Buscar de acordo com o id
    public Optional<Evento> getById(int id) {
        var query = "SELECT * from \"T_SOS_EVENTO\" where id_evento = ?";
        try (var connection = DatabaseConfig.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {

            // Definir o parâmetro na query
            preparedStatement.setInt(1, id);

            // Executar a consulta ao banco
            var result = preparedStatement.executeQuery();

            // Se encontrar um resultado, cria o objeto evento e retorna
            if (result.next()) {
                Evento evento = new Evento();
                evento.setIdEvento(result.getInt("id_evento"));
                evento.setNomeEvento(result.getString("nome_evento"));
                evento.setDescricaoEvento(result.getString("descricao_evento"));
                evento.setCausas(result.getString("causas"));
                evento.setAlertas(result.getString("alertas"));
                evento.setAcoesAntes(result.getString("acoes_antes"));
                evento.setAcoesDurante(result.getString("acoes_durante"));
                evento.setAcoesDepois(result.getString("acoes_depois"));

                return Optional.of(evento);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // Caso nenhum registro seja encontrado, retorna Optional.empty()
        return Optional.empty();

    }

    //Atualizar/mudar alguma informação
    public boolean atualizar(int id, Evento evento) {
        String query = "UPDATE T_SOS_EVENTO SET nome_evento = ?, descricao_evento = ?, causas = ?, alertas = ?, " +
                "acoes_antes = ?, acoes_durante = ?, acoes_depois = ? WHERE id_evento = ?";

        try (var connection = DatabaseConfig.getConnection();
             var stmt = connection.prepareStatement(query)) {

            stmt.setString(1, evento.getNomeEvento());
            stmt.setString(2, evento.getDescricaoEvento());
            stmt.setString(3, evento.getCausas());
            stmt.setString(4, evento.getAlertas());
            stmt.setString(5, evento.getAcoesAntes());
            stmt.setString(6, evento.getAcoesDurante());
            stmt.setString(7, evento.getAcoesDepois());
            stmt.setInt(8, id);
            stmt.executeUpdate();

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;  // true se encontrou e atualizou
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
