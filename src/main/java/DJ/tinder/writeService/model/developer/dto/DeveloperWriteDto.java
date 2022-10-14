package DJ.tinder.writeService.model.developer.dto;

import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class DeveloperWriteDto {

    private String firstName;
    private String lastName;
    private String description;
    private String profession;
    private List<AchievementWriteDto> achievements;
    private List<SkillWriteDto> skills;
}
