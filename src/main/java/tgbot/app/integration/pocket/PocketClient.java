package tgbot.app.integration.pocket;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tgbot.app.integration.pocket.model.PocketRequestDTO;
import tgbot.app.integration.pocket.model.PocketResponse;


@FeignClient(
        name = "pocket",
        url = "https://getpocket.com/v3/"
)
public interface PocketClient {
    @PostMapping("/add")
    PocketResponse addPageInPocket(@RequestBody PocketRequestDTO pocketRequestDTO);
}
