package fiap.tds.entity;

import java.util.Objects;

public class Evento {
    private int idEvento;
    private String nomeEvento;
    private String descricaoEvento;
    private String causas;
    private String alertas;
    private String acoesAntes;
    private String acoesDurante;
    private String acoesDepois;

    public Evento() {
    }

    public Evento(int idEvento, String nomeEvento, String descricaoEvento, String causas, String alertas, String acoesAntes, String acoesDurante, String acoesDepois) {
        this.idEvento = idEvento;
        this.nomeEvento = nomeEvento;
        this.descricaoEvento = descricaoEvento;
        this.causas = causas;
        this.alertas = alertas;
        this.acoesAntes = acoesAntes;
        this.acoesDurante = acoesDurante;
        this.acoesDepois = acoesDepois;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public String getCausas() {
        return causas;
    }

    public void setCausas(String causas) {
        this.causas = causas;
    }

    public String getAlertas() {
        return alertas;
    }

    public void setAlertas(String alertas) {
        this.alertas = alertas;
    }

    public String getAcoesAntes() {
        return acoesAntes;
    }

    public void setAcoesAntes(String acoesAntes) {
        this.acoesAntes = acoesAntes;
    }

    public String getAcoesDurante() {
        return acoesDurante;
    }

    public void setAcoesDurante(String acoesDurante) {
        this.acoesDurante = acoesDurante;
    }

    public String getAcoesDepois() {
        return acoesDepois;
    }

    public void setAcoesDepois(String acoesDepois) {
        this.acoesDepois = acoesDepois;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return idEvento == evento.idEvento && Objects.equals(nomeEvento, evento.nomeEvento) && Objects.equals(descricaoEvento, evento.descricaoEvento) && Objects.equals(causas, evento.causas) && Objects.equals(alertas, evento.alertas) && Objects.equals(acoesAntes, evento.acoesAntes) && Objects.equals(acoesDurante, evento.acoesDurante) && Objects.equals(acoesDepois, evento.acoesDepois);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, nomeEvento, descricaoEvento, causas, alertas, acoesAntes, acoesDurante, acoesDepois);
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", nomeEvento='" + nomeEvento + '\'' +
                ", descricaoEvento='" + descricaoEvento + '\'' +
                ", causas='" + causas + '\'' +
                ", alertas='" + alertas + '\'' +
                ", acoesAntes='" + acoesAntes + '\'' +
                ", acoesDurante='" + acoesDurante + '\'' +
                ", acoesDepois='" + acoesDepois + '\'' +
                '}';
    }
}
