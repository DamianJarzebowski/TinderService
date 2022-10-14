package DJ.tinder.readService.model.achievement.dto;

import DJ.tinder.readService.model.achievement.Achievement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementReadMapper {

    public AchievementReadDto toDto(Achievement achievement) {
        return new AchievementReadDto()
                .setId(achievement.getId())
                .setName(achievement.getName());
    }

    public List<AchievementReadDto> toDto(List<Achievement> achievementList) {
        List<AchievementReadDto> dtoList = new ArrayList<>();
        for (Achievement achievement: achievementList) {
            dtoList.add(toDto(achievement));
        }
        return dtoList;
    }

}
