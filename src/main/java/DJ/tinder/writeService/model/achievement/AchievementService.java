package DJ.tinder.writeService.model.achievement;

import DJ.tinder.readService.model.achievement.Achievement;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;

import java.util.List;

public interface AchievementService {

    List<Achievement> findAll();

    Achievement findById(Long id);

    Achievement create(AchievementWriteDto dto);

    Achievement update(Long id, AchievementWriteDto dto);
}
