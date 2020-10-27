package tgbot.app.integration.trello;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tgbot.app.integration.trello.model.Board;
import tgbot.app.integration.trello.model.BoardList;
import tgbot.app.integration.trello.model.CreatedCard;
import tgbot.app.integration.trello.model.CreatedCheckList;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HttpTrelloService implements TrelloService {

    private final TrelloClient client;

    @Override
    public CreatedCard createCard(String boardName, String listName, TrelloCard card) {

        final Board board = findBoardByName(boardName);

        final BoardList boardList = client.boardLists(board.getId()).stream()
                .filter(list -> list.getName().equals(listName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("List doesn't exist: %s", listName)));

        final List<String> labelsId = card.getLabels().stream().map(TrelloCard.Label::getId).collect(Collectors.toList());

        final CreatedCard createdCard = client.createCard(card.getTitle(), card.getDescription(), boardList.getId(), labelsId);

        if (card.getCheckList() != null) {
            CreatedCheckList createdCheckList =
                    client.createCheckList(createdCard.getId(), card.getCheckList().getTitle());

            int numeration = 0;
            for (final String item : card.getCheckList().getItems()) {
                if (numeration % 200 == 0 && numeration != 0) {
                    createdCheckList = client.createCheckList(createdCard.getId(), card.getCheckList().getTitle());
                }
                client.createCheckListItem(createdCheckList.getId(), ++numeration + ") " + item);
            }
        }
        return createdCard;
    }

    public Board findBoardByName(String boardName) {
        return client.boards().stream().filter(b -> b.getName().equals(boardName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Board doesn't exist: %s", boardName)));
    }
}
