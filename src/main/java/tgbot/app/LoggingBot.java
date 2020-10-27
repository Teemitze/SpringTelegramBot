package tgbot.app;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import tgbot.app.configuration.BotProperties;
import tgbot.app.integration.trello.TrelloCard;
import tgbot.app.integration.trello.TrelloService;
import tgbot.app.integration.trello.model.CreatedCard;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingBot extends TelegramLongPollingBot {

    private final BotProperties properties;
    private final TrelloService trelloService;
    private final FactoryHandler factoryHandler;

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (!update.getMessage().getFrom().getUserName().equals("Teemitze")) {
            this.execute(
                    new SendMessage(update.getMessage().getChatId(), "Hehe hehe... You have no power here...")
            );
            return;
        }

        log.info("Got an update: {}", update);

        final String message = update.getMessage().getText();

        log.info("Fetching a playlist: {}", message);

        final String idBoard = trelloService.findBoardByName("Board").getId();

        final TrelloCard trelloCard = factoryHandler.createHandler(message).fillCardByURL(message, idBoard);

        log.info("Creating a trello card: {}", trelloCard);
        final CreatedCard createdCard = trelloService.createCard("Board", "To Do", trelloCard);

        log.info("The card's been created: {}", createdCard);
        this.execute(
                new SendMessage(update.getMessage().getChatId(),
                        String.format("The card's \"%s\" been created!", trelloCard.getTitle()))
        );
    }

    @Override
    public String getBotUsername() {
        return properties.getName();
    }

    @Override
    public String getBotToken() {
        return properties.getToken();
    }
}
