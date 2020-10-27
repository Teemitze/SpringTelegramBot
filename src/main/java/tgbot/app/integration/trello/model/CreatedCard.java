package tgbot.app.integration.trello.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@NoArgsConstructor
public class CreatedCard {
    private String id;
    private URL shortUrl;
}
