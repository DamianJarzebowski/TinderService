package DJ.tinder.writeService.model.achievement;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.badRequest.BadRequestException;
import DJ.tinder.exception.notFound.NotFoundException;
import DJ.tinder.readService.model.achievement.Achievement;
import DJ.tinder.readService.model.achievement.AchievementRepository;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AchievementServiceImplWrite implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    public List<Achievement> findAll() {
        log.info("Downloading all achievements");
        return achievementRepository.findAll();
    }

    @Override
    public Achievement findById(Long id) {
        log.info(String.format("Downloading achievement by id %d", id));
        return achievementRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(String.format("Achievement id: %d does not exists", id));
                    return new NotFoundException(ErrorMessage.NOT_FOUND);
                });

    }

    @Override
    public Achievement create(AchievementWriteDto dto) {
        log.info(String.format("Creating new achievement: %s", dto));
        validateName(dto.getName());
        return achievementRepository.save(
                Achievement.builder()
                        .name(dto.getName())
                        .build());
    }

    @Override
    public Achievement update(Long id, AchievementWriteDto dto) {
        log.info(String.format("Updating achievement: %s , id: %d", dto, id));
        validateName(dto.getName());
        return achievementRepository.findById(id)
                .map(achievementFromDb -> {
                    achievementFromDb.setName(dto.getName());
                    log.info(String.format("Achievement %s updated, new name: %s", achievementFromDb.getName(), dto));
                    return achievementRepository.save(achievementFromDb);
                }).orElseThrow(() -> {
                    log.error(String.format("Achievement id: %d does not exists", id));
                    return new NotFoundException(ErrorMessage.NOT_FOUND);
                });
    }

    private void validateName(String name) {
        if (name.length() < 3) {
            log.error(String.format("Invalid achievement name %s", name));
            throw new BadRequestException(ErrorMessage.BAD_REQUEST);
        }
    }
}
