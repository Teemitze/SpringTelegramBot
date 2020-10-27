package tgbot.app.integration.coursehunter;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class HtmlCourseHunterClient implements CourseHunterClient {
    @SneakyThrows
    @Override
    public Document tutorialPage(String courseURL) {
        return Jsoup.connect(courseURL).get();
    }
}
