package DJ.tinder.writeService.model.developer;

import DJ.tinder.readService.model.achievement.Achievement;
import DJ.tinder.readService.model.developer.Developer;
import DJ.tinder.readService.model.skill.Skill;

import java.util.List;

public interface DeveloperService {

    Developer create(Developer developerDTO);

    Developer updatePersonalInformation(Long id, Developer developer);

    Developer updateAchievements(Long id, List<Achievement> achievements);

    Developer updateSkills(Long id, List<Skill> skills);

}
