package tgbot.app.integration.trello;

import tgbot.app.integration.trello.model.Board;
import tgbot.app.integration.trello.model.CreatedCard;

public interface TrelloService {
    CreatedCard createCard(String board, String list, TrelloCard card);

    Board findBoardByName(String boardName);
}
