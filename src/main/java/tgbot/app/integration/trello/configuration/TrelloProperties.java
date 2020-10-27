package tgbot.app.integration.trello.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "trello")
public class TrelloProperties {
    private String apiKey;
    private String token;
}
