package tgbot.app.integration.youtube.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PlaylistInfo {
    private String title;
    private List<String> videoTitles;
}