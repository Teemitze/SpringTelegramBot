package tgbot.app.integration.pocket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tgbot.app.integration.pocket.model.PocketRequestDTO;
import tgbot.app.integration.pocket.model.PocketResponse;

@Service
@RequiredArgsConstructor
public class HttpPocketService implements PocketService {

    private final PocketClient pocketClient;

    @Override
    public PocketResponse fetchPocketResponse(@RequestBody PocketRequestDTO pocketRequestDTO) {
        return pocketClient.addPageInPocket(pocketRequestDTO);
    }
}
