package DJ.tinder.readService.model.achievement;

import java.util.List;

public interface AchievementService {

    List<Achievement> findAll();

    Achievement findById(Long id);

}
