package tgbot.app.integration.pocket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tgbot.app.integration.Handler;
import tgbot.app.integration.pocket.configuration.PocketProperties;
import tgbot.app.integration.pocket.model.PocketRequestDTO;
import tgbot.app.integration.pocket.model.PocketResponse;
import tgbot.app.integration.trello.TrelloCard;
import tgbot.app.integration.trello.TrelloClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HandlerPocket implements Handler {

    private final PocketClient pocketClient;
    private final PocketProperties pocketProperties;
    private final TrelloClient trelloClient;

    @Override
    public TrelloCard fillCardByURL(String URL, String boardId) {
        final PocketResponse pocketResponse = pocketClient.addPageInPocket(createPocketRequestDTO(URL));

        final List<TrelloCard.Label> labels = trelloClient.labels(boardId)
                .stream().filter(label -> label.getName().equals("Pocket")).collect(Collectors.toList());

        return new TrelloCard(pocketResponse.getItem().getTitle(), URL, labels, null);
    }


    private PocketRequestDTO createPocketRequestDTO(String url) {
        final PocketRequestDTO pocketRequestDTO = new PocketRequestDTO();
        pocketRequestDTO.setUrl(url);
        pocketRequestDTO.setAccess_token(pocketProperties.getToken());
        pocketRequestDTO.setConsumer_key(pocketProperties.getApiKey());
        return pocketRequestDTO;
    }
}
