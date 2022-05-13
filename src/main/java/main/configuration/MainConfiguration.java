package main.configuration;

import main.cache.PatientCache;
import main.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
public class MainConfiguration {

    @Autowired
    private URLConfiguration urlConfiguration;

    @Autowired
    private ConfigurableApplicationContext ctx;

    @Bean
    public PatientCache patientCache() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Patient[]> response = restTemplate.getForEntity(urlConfiguration.getPatientURL(), Patient[].class);
            Map<Integer, Patient> patientMap = new HashMap<>();
            for (Patient patient : Objects.requireNonNull(response.getBody())) {
                patientMap.put(patient.getId(), patient);
            }
            return new PatientCache(patientMap);
        } catch (ResourceAccessException e) {
            System.err.println("PatientService not available");
            int exitCode = SpringApplication.exit(ctx, () -> 1);
            System.exit(exitCode);
            return null;
        } catch (NullPointerException e) {
            System.out.println("PatientCache is empty");
            return new PatientCache(new HashMap<>());
        }
    }
}
