package tgbot.app.integration.youtube;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tgbot.app.integration.youtube.configuration.YoutubeClientConfiguration;
import tgbot.app.integration.youtube.model.PlaylistPage;
import tgbot.app.integration.youtube.model.PlaylistTitle;

@FeignClient(
        name = "youtube",
        url = "https://www.googleapis.com/youtube/v3/",
        configuration = YoutubeClientConfiguration.class
)
public interface YoutubeClient {

    @GetMapping("/playlistItems")
    PlaylistPage playlistPage(@RequestParam String part,
                              @RequestParam String playlistId,
                              @RequestParam String fields,
                              @RequestParam int maxResults,
                              @RequestParam String pageToken);

    @GetMapping("/playlists")
    PlaylistTitle playlistTitle(@RequestParam String part,
                                @RequestParam String id,
                                @RequestParam String fields);

}
