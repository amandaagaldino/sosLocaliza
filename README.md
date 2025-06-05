# 🌐 SOS Localiza

O **SOS Localiza** é uma plataforma de resposta rápida para situações de risco climático, como enchentes e deslizamentos. Utilizando geolocalização, mapas interativos e envio de SMS emergencial, o sistema conecta a população em perigo diretamente com órgãos competentes.

---
## Contexto

A natureza apresenta eventos climáticos extremos, como tempestades intensas, ventos fortes, inundações e deslizamentos. Com o aumento da ocorrência desses eventos, é fundamental criar soluções tecnológicas que auxiliem na prevenção, orientação e resposta rápida em situações de risco.

Este projeto foi desenvolvido como parte do desafio da FIAP para a disciplina *Domain Driven Design Using Java*, com integração com um banco de dados Oracle para gerenciar informações sobre eventos climáticos adversos e a API Twilio para o envio de alertas.

---

## 🔧 Tecnologias
- Java + Quarkus
- Twilio (envio de SMS)
- API Stream do Java
---
## 🚨 Funcionalidades
- Envio de mensagens emergenciais via SMS
- Orientações preventivas sobre como agir em situações de risco

## 📦 Executando o projeto

```bash
./mvnw quarkus:dev
```

---

## Estrutura do Banco de Dados

- `T_SOS_EVENTOS`: informações e orientações sobre eventos climáticos extremos.
- `T_SOS_SMS`: registros de mensagens SMS enviadas.

---

## Considerações Finais

Este sistema foi desenvolvido para atender aos requisitos do desafio FIAP "Eventos Extremos", combinando funcionalidades de banco de dados relacional com integração com a API externas Twilio. O foco é auxiliar na prevenção e resposta rápida em situações de risco, utilizando tecnologia acessível e interativa.

---

# Participantes
<p>➢Amanda Galdino - RM:560066 
<br>➢Bruno Cantacini - RM:560242
<br>➢Gustavo Gonçalves - RM:556823
