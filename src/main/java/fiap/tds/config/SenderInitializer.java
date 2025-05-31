package fiap.tds.config;

import com.twilio.Twilio;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.logging.Logger;

@ApplicationScoped
public class SenderInitializer {
    public static Logger logger = Logger.getLogger(SenderInitializer.class.getName());

    public SenderInitializer(TwilioConfiguration twilioConfiguration){
        Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());

        logger.info("Twilio initialized with account sid: " + twilioConfiguration.getAccountSid() +
                            " Auth token: " + twilioConfiguration.getAuthToken() +
                            " and trial number: " + twilioConfiguration.getTrialNumber());
    }
}
