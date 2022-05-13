package main.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "url")
public class URLConfiguration {

    private String patientURL;

    public String getPatientURL() {
        return patientURL;
    }

    public void setPatientURL(String patientURL) {
        this.patientURL = patientURL;
    }
}
