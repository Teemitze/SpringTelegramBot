package tgbot.app.integration.youtube.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
public class PlaylistTitle {
    private List<Item> items;

    public Optional<String> findFirstTitle() {
        return items.stream().findFirst().map(item -> item.getSnippet().getLocalized().getTitle());
    }

    @Data
    @NoArgsConstructor
    public static class Item {

        private Snippet snippet;

        @Data
        @NoArgsConstructor
        public static class Snippet {
            private Localized localized;

            @Data
            @NoArgsConstructor
            public static class Localized {
                private String title;
            }
        }
    }
}
