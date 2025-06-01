package fiap.tds.repository;

import fiap.tds.entity.SmsMessage;
import fiap.tds.infrastructure.DatabaseConfig;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SmsRepository {

    public void salvar(SmsMessage smsMessage) {
        String sql = """
            INSERT INTO "T_SOS_SMS" 
            (sender, phone_number, message, timestamp, enviado_com_sucesso, erro)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (var connection = DatabaseConfig.getConnection();
             var stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, smsMessage.getSender());
            stmt.setString(2, smsMessage.getPhoneNumber());
            stmt.setString(3, smsMessage.getMessage());
            stmt.setTimestamp(4, Timestamp.valueOf(smsMessage.getTimestamp()));
            stmt.setString(5, smsMessage.getEnviadoComSucesso());
            stmt.setString(6, smsMessage.getErro());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar SMS no banco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<SmsMessage> getAll() {
        var smsMessages = new ArrayList<SmsMessage>();
        var query = "SELECT * FROM \"T_SOS_SMS\" ORDER BY timestamp DESC";

        try (var connection = DatabaseConfig.getConnection()) {
            var stmt = connection.prepareStatement(query);
            var result = stmt.executeQuery();

            while (result.next()) {
                var sms = new SmsMessage();
                sms.setId(result.getInt("id_sms"));
                sms.setSender(result.getString("sender"));
                sms.setPhoneNumber(result.getString("phone_number"));
                sms.setMessage(result.getString("message"));
                sms.setTimestamp(result.getTimestamp("timestamp").toLocalDateTime());
                sms.setEnviadoComSucesso(result.getString("enviado_com_sucesso"));
                sms.setErro(result.getString("erro"));

                smsMessages.add(sms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return smsMessages;
    }
}
