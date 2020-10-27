package tgbot.app.integration.youtube;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tgbot.app.integration.youtube.model.PlaylistInfo;
import tgbot.app.integration.youtube.model.PlaylistPage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HttpYoutubeService implements YoutubeService {

    private final YoutubeClient client;

    private static final String PART = "snippet";
    private static final String FIELDS_TITLE = "items(snippet(localized(title)))";
    private static final String FIELDS_PLAYLIST = "pageInfo,nextPageToken,items(snippet(title))";

    @Override
    public PlaylistInfo fetchPlaylistInfo(String playlistId) {
        final String playlistTitle = client.playlistTitle(PART, playlistId, FIELDS_TITLE)
                .findFirstTitle()
                .orElseThrow(() -> new RuntimeException(
                        String.format("Failed to get the playlist title. PlaylistId: %s", playlistId))
                );
        return new PlaylistInfo(playlistTitle, fetchVideoTitles(playlistId));
    }

    private List<String> fetchVideoTitles(String playlistId) {
        final Stream<PlaylistPage> pages = Stream.iterate(
                client.playlistPage(PART, playlistId, FIELDS_PLAYLIST, 50, null),
                Objects::nonNull,
                page -> page.getNextPageToken() == null ? null :
                        client.playlistPage(PART, playlistId, FIELDS_PLAYLIST, 50, page.getNextPageToken())
        );
        return pages.flatMap(page -> page.getItems().stream())
                .map(item -> item.getSnippet().getTitle())
                .collect(Collectors.toList());
    }
}
