package tgbot.app.integration.pocket;

import org.springframework.web.bind.annotation.RequestBody;
import tgbot.app.integration.pocket.model.PocketRequestDTO;
import tgbot.app.integration.pocket.model.PocketResponse;

public interface PocketService {
    PocketResponse fetchPocketResponse(@RequestBody PocketRequestDTO pocketRequestDTO);
}
