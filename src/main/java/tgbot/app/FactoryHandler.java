package tgbot.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tgbot.app.integration.Handler;
import tgbot.app.integration.coursehunter.HandlerCourseHunter;
import tgbot.app.integration.pocket.HandlerPocket;
import tgbot.app.integration.youtube.HandlerYoutube;

@Component
@RequiredArgsConstructor
public class FactoryHandler {

    private final HandlerYoutube handlerYoutube;
    private final HandlerPocket handlerPocket;
    private final HandlerCourseHunter handlerCourseHunter;

    public Handler createHandler(String url) {
        if (url.matches("^https?+://(www\\.)?youtube.com/playlist\\?list=.{18,34}$")) {
            return handlerYoutube;
        } else if (url.matches("^https?+://(www\\.)?coursehunter.net/course/.*$")) {
            return handlerCourseHunter;
        } else {
            return handlerPocket;
        }
    }
}
