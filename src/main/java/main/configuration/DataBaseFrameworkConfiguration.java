package main.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "database")
public class DataBaseFrameworkConfiguration {

    private String framework;

    public String getFramework() {
        return framework;
    }

    public void setFramework(String framework) {
        if (framework == null || framework.isEmpty()) {
            this.framework = "mybatis";
        } else {
            this.framework = framework;
        }
        System.out.println(this.framework);
    }
}
