package tgbot.app.integration.youtube.configuration;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class YoutubeClientConfiguration {

    private final YoutubeProperties properties;

    @Bean
    public RequestInterceptor youtubeRequestInterceptor() {
        return template -> template.query("key", properties.getApiKey());
    }
}
