package fiap.tds.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class SmsMessage {
    private int id;
    private String sender;
    private String phoneNumber;
    private String message;
    private LocalDateTime timestamp;

    private String enviadoComSucesso;
    private String erro; //null se nao teve erro


    public SmsMessage() {
    }

    public SmsMessage(int id, String sender, String phoneNumber, String message, LocalDateTime timestamp, String enviadoComSucesso, String erro) {
        this.id = id;
        this.sender = sender;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.timestamp = timestamp;
        this.enviadoComSucesso = enviadoComSucesso;
        this.erro = erro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getEnviadoComSucesso() {
        return enviadoComSucesso;
    }

    public void setEnviadoComSucesso(String enviadoComSucesso) {
        this.enviadoComSucesso = enviadoComSucesso;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SmsMessage that = (SmsMessage) o;
        return id == that.id && Objects.equals(sender, that.sender) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(message, that.message) && Objects.equals(timestamp, that.timestamp) && Objects.equals(enviadoComSucesso, that.enviadoComSucesso) && Objects.equals(erro, that.erro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, phoneNumber, message, timestamp, enviadoComSucesso, erro);
    }

    @Override
    public String toString() {
        return "SmsMessage{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", enviadoComSucesso='" + enviadoComSucesso + '\'' +
                ", erro='" + erro + '\'' +
                '}';
    }
}
