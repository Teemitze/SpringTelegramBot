package tgbot.app.integration.coursehunter;

import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import tgbot.app.integration.coursehunter.model.CourseHunterTutorialDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HtmlCourseHunterService implements CourseHunterService {

    private final CourseHunterClient courseHunterClient;

    @Override
    public CourseHunterTutorialDTO createCourseHunterTutorialDTO(String courseURL) {

        final Document document = courseHunterClient.tutorialPage(courseURL);

        final String title = document.getElementsByClass("hero-description").first().text();
        final List<String> lessonsName = document
                .getElementById("lessons-list").getElementsByClass("lessons-name").eachText();

        return new CourseHunterTutorialDTO(title, lessonsName);
    }
}
