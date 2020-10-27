package tgbot.app.integration.pocket.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PocketResponse {

    private Item item;

    @Data
    @NoArgsConstructor
    public static class Item {
        private String title;
    }
}
