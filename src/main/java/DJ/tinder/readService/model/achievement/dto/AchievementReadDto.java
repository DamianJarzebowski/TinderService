package DJ.tinder.readService.model.achievement.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AchievementReadDto {

    private Long id;
    private String name;

}
