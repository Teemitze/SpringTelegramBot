package tgbot.app.integration.coursehunter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tgbot.app.integration.Handler;
import tgbot.app.integration.coursehunter.model.CourseHunterTutorialDTO;
import tgbot.app.integration.trello.TrelloCard;
import tgbot.app.integration.trello.TrelloClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HandlerCourseHunter implements Handler {

    private final CourseHunterService courseHunterService;
    private final TrelloClient trelloClient;

    @Override
    public TrelloCard fillCardByURL(String URL, String boardId) {
        final CourseHunterTutorialDTO tutorial = courseHunterService.createCourseHunterTutorialDTO(URL);

        final TrelloCard.TrelloCardCheckList trelloCardCheckList =
                new TrelloCard.TrelloCardCheckList("Чек-лист", tutorial.getLessonsName());

        final List<TrelloCard.Label> labels = trelloClient.labels(boardId)
                .stream().filter(label -> label.getName().equals("CourseHunter")).collect(Collectors.toList());
        return new TrelloCard(tutorial.getTitle(), URL, labels, trelloCardCheckList);
    }
}
