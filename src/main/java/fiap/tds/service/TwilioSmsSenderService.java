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
import fiap.tds.entity.SmsMessage;
import fiap.tds.repository.SmsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PostConstruct;

@ApplicationScoped
public class TwilioSmsSenderService implements SmsSender{

    private final SmsRepository smsRepository = new SmsRepository();
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
        var sms = new SmsMessage();
        String to = "+55" + smsRequest.ddd() + smsRequest.phoneNumber();
        String from = "De: " + smsRequest.sender() + "\n";

        sms.setSender(smsRequest.sender());
        sms.setPhoneNumber(to);
        sms.setMessage(smsRequest.message());
        sms.setTimestamp(java.time.LocalDateTime.now());

        try {

            if (!isValidPhoneNumber(to)) {
                sms.setEnviadoComSucesso(String.valueOf(false));
                sms.setErro("Número inválido");
                smsRepository.salvar(sms);
                throw new RuntimeException("Número de telefone inválido: " + to);
            }

            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber(to), //Destinatario
                    new PhoneNumber(twilioConfiguration.getTrialNumber()),
                    from + smsRequest.message()
            ).create();

            sms.setEnviadoComSucesso(String.valueOf(true));
            sms.setErro(null);

        } catch (Exception e) {
            sms.setEnviadoComSucesso(String.valueOf(false));
            sms.setErro(e.getMessage());
            e.printStackTrace();
            throw new ApiException("Erro ao enviar SMS: " + e.getMessage());
        } finally {
            smsRepository.salvar(sms); //salvar com ou sem erro
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
