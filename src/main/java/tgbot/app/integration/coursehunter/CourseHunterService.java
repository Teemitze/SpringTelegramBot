package tgbot.app.integration.coursehunter;

import tgbot.app.integration.coursehunter.model.CourseHunterTutorialDTO;

public interface CourseHunterService {
    CourseHunterTutorialDTO createCourseHunterTutorialDTO(String courseURL);
}
