package DJ.tinder.writeService.model.developer;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.notFound.NotFoundException;

import DJ.tinder.readService.model.achievement.Achievement;
import DJ.tinder.readService.model.developer.Developer;
import DJ.tinder.readService.model.developer.DeveloperRepository;
import DJ.tinder.readService.model.skill.Skill;
import DJ.tinder.writeService.model.achievement.AchievementService;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import DJ.tinder.writeService.model.skill.SkillService;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeveloperServiceImplWrite implements DeveloperService{

    private final DeveloperRepository developerRepository;
    private final SkillService skillService;
    private final AchievementService achievementService;

    @Override
    public Developer create(Developer developer) {
        log.info("Creating developer");
        return developerRepository.save(developer);
    }

    @Override
    public Developer updatePersonalInformation(Long id, Developer developer) {
        log.info(String.format("Updating developer by id: %d", id));
        return developerRepository.findById(id)
                .map(developerFromDb -> {
                    developerFromDb.setFirstName(developer.getFirstName());
                    developerFromDb.setLastName(developer.getLastName());
                    developerFromDb.setDescription(developer.getDescription());
                    developerFromDb.setProfession(developer.getProfession());
                    log.info("Developer updated");
                    return developerRepository.save(developerFromDb);
                }).orElseThrow(() -> {
                    log.error(String.format("Developer id: %d does not exists", id));
                    return new NotFoundException(ErrorMessage.NOT_FOUND);
                });
    }

    @Override
    public Developer updateAchievements(Long id, List<AchievementWriteDto> dtoList) {
        List<Achievement> achievements = new ArrayList<>();
        for (AchievementWriteDto dto : dtoList) {
            Achievement achievement = achievementService.create(dto);
            achievements.add(achievement);
        }
        return developerRepository.findById(id)
                .map(developerFromDb -> {
                    developerFromDb.setAchievements(achievements);
                    return developerRepository.save(developerFromDb);
                }).orElseThrow(() -> {
                    log.error(String.format("Developer id: %d does not exists", id));
                    return new NotFoundException(ErrorMessage.NOT_FOUND);
                });
    }

    @Override
    public Developer updateSkills(Long id, List<SkillWriteDto> dtoList) {
        List<Skill> skills = new ArrayList<>();
        for (SkillWriteDto dto : dtoList) {
            Skill skill = skillService.create(dto);
            skills.add(skill);
        }
        return  developerRepository.findById(id)
                .map(developerFromDb -> {
                    developerFromDb.setSkills(skills);
                    return developerRepository.save(developerFromDb);
                }).orElseThrow(() -> {
                    log.error(String.format("Developer id: %d does not exists", id));
                    return new NotFoundException(ErrorMessage.NOT_FOUND);
                });
    }

}
