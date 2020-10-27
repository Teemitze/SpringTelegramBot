package tgbot.app.integration.coursehunter;


import org.jsoup.nodes.Document;


public interface CourseHunterClient {
    Document tutorialPage(String courseURL);
}
