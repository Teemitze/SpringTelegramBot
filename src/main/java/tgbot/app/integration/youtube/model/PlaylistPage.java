package tgbot.app.integration.youtube.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlaylistPage {

    private String nextPageToken;
    private List<Item> items;
    private PageInfo pageInfo;

    @Data
    @NoArgsConstructor
    public static class Item {

        private Snippet snippet;

        @Data
        @NoArgsConstructor
        public static class Snippet {
            private String title;
        }
    }

    @Data
    @NoArgsConstructor
    public static class PageInfo {
        private int totalResults;
        private int resultsPerPage;
    }
}