package tgbot.app.integration.trello;

import lombok.*;

import javax.annotation.Nullable;
import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class TrelloCard {
    private final String title;
    private final String description;
    private final List<Label> labels;
    @Nullable
    @ToString.Exclude
    private final TrelloCardCheckList checkList;

    @Getter
    @RequiredArgsConstructor
    public static class TrelloCardCheckList {
        private final String title;
        private final List<String> items;
    }

    @Data
    @NoArgsConstructor
    public static class Label {
        private String id;
        private String idBoard;
        private String name;
        private String color;
    }
}
