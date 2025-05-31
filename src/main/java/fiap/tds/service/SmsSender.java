package fiap.tds.service;

import fiap.tds.dto.SmsRequest;

public interface SmsSender {
    void sendSms(SmsRequest smsRequest);
}
