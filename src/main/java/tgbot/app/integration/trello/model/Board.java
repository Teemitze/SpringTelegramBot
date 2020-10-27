package tgbot.app.integration.trello.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Board {
    private String id;
    private String name;
}