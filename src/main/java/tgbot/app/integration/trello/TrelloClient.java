package tgbot.app.integration.trello;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tgbot.app.integration.trello.configuration.TrelloClientConfiguration;
import tgbot.app.integration.trello.model.*;

import java.util.List;

@FeignClient(name = "trello", url = "https://api.trello.com/1/", configuration = TrelloClientConfiguration.class)
public interface TrelloClient {

    @GetMapping("/members/me/boards")
    List<Board> boards();

    @GetMapping("/boards/{id}/lists")
    List<BoardList> boardLists(@PathVariable String id);

    @PostMapping("/cards")
    CreatedCard createCard(@RequestParam String name, @RequestParam String desc, @RequestParam String idList, @RequestParam List<String> idLabels);

    @PostMapping("/checklists")
    CreatedCheckList createCheckList(@PathVariable String idCard, @RequestParam String name);

    @PostMapping("/checklists/{id}/checkItems")
    CreatedCheckListItem createCheckListItem(@PathVariable String id, @RequestParam String name);

    @GetMapping("/boards/{id}/labels")
    List<TrelloCard.Label> labels(@PathVariable String id);
}
