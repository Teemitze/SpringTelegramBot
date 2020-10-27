package tgbot.app.integration;

import tgbot.app.integration.trello.TrelloCard;

public interface Handler {
    TrelloCard fillCardByURL(String URL, String boardId);
}
