package tgbot.app.integration.trello.configuration;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class TrelloClientConfiguration {

    private final TrelloProperties properties;

    @Bean
    public RequestInterceptor trelloRequestInterceptor() {
        return template ->
                template.query("key", properties.getApiKey()).query("token", properties.getToken());
    }
}
