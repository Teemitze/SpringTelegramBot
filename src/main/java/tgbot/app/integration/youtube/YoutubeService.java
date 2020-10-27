package tgbot.app.integration.youtube;

import tgbot.app.integration.youtube.model.PlaylistInfo;

public interface YoutubeService {
    PlaylistInfo fetchPlaylistInfo(String playlistId);
}
