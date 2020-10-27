package tgbot.app.integration.youtube.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "youtube")
public class YoutubeProperties {
    private String apiKey;
}
