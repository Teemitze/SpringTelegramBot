package tgbot.app.integration.youtube;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tgbot.app.integration.Handler;
import tgbot.app.integration.trello.TrelloCard;
import tgbot.app.integration.trello.TrelloClient;
import tgbot.app.integration.youtube.model.PlaylistInfo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HandlerYoutube implements Handler {

    private final YoutubeService youtubeService;
    private final TrelloClient trelloClient;

    @Override
    public TrelloCard fillCardByURL(String URL, String boardId) {
        final PlaylistInfo playlistInfo = youtubeService.fetchPlaylistInfo(URL.trim().substring(38));

        final TrelloCard.TrelloCardCheckList trelloCardCheckList =
                new TrelloCard.TrelloCardCheckList("Чек-лист", playlistInfo.getVideoTitles());

        final List<TrelloCard.Label> labels = trelloClient.labels(boardId)
                .stream().filter(label -> label.getName().equals("YouTube")).collect(Collectors.toList());
        return new TrelloCard(playlistInfo.getTitle(), URL, labels, trelloCardCheckList);
    }
}
