package tgbot.app.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Data
@Configuration
@ConfigurationProperties(prefix = "telegram-bot")
public class BotProperties {
    @NotNull
    private String token;
    @NotNull
    private String name;
}
