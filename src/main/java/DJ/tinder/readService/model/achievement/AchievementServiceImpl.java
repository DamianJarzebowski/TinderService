package DJ.tinder.readService.model.achievement;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.notFound.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AchievementServiceImpl implements AchievementService {

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
}
