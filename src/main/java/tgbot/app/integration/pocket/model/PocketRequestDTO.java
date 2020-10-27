package tgbot.app.integration.pocket.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PocketRequestDTO {
    private String url;
    private String consumer_key;
    private String access_token;
}
