package tgbot.app.integration.pocket.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "pocket")
public class PocketProperties {
    private String apiKey;
    private String token;
}
