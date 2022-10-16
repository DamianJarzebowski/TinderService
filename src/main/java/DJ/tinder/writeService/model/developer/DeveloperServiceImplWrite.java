package DJ.tinder.writeService.model.developer;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.notFound.NotFoundException;
import DJ.tinder.readService.model.achievement.Achievement;
import DJ.tinder.readService.model.developer.Developer;
import DJ.tinder.readService.model.developer.DeveloperRepository;
import DJ.tinder.readService.model.skill.Skill;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeveloperServiceImplWrite implements DeveloperService{

    private final DeveloperRepository developerRepository;

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
    public Developer updateAchievements(Long id, List<Achievement> achievements) {
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
    public Developer updateSkills(Long id, List<Skill> skills) {
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
