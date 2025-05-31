package fiap.tds.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import fiap.tds.config.TwilioConfiguration;
import fiap.tds.dto.SmsRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;

@ApplicationScoped
public class TwilioSmsSenderService implements SmsSender{
    public final TwilioConfiguration twilioConfiguration;

    public TwilioSmsSenderService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @PostConstruct
    void initTwilio() {
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getAuthToken()
        );
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        try {

            String to = "+55" + smsRequest.ddd() + smsRequest.phoneNumber();
            String from = "De: " + smsRequest.sender() + "\n";

            if (!isValidPhoneNumber(to)) {
                throw new RuntimeException("Número de telefone inválido: " + to);
            }

            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(to), //Destinatario
                    new PhoneNumber(twilioConfiguration.getTrialNumber()),
                    from + smsRequest.message()
            ).create();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException("Erro ao enviar SMS: " + e.getMessage());
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, "BR");
            return phoneUtil.isValidNumber(numberProto);
        } catch (NumberParseException e) {
            return false;
        }
    }
}
