package fiap.tds.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.DefaultValue;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class TwilioConfiguration {
    private final String accountSid;
    private final String authToken;
    private final String trialNumber;

    public TwilioConfiguration(@ConfigProperty(name = "twilio.account.sid") String accountSid,
                               @ConfigProperty(name = "twilio.auth.token") String authToken,
                               @ConfigProperty(name = "twilio.trial.number") String trialNumber) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.trialNumber = trialNumber;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getTrialNumber() {
        return trialNumber;
    }
}
