package DJ.tinder.readService.model.developer.dto;

import DJ.tinder.readService.model.achievement.dto.AchievementReadDto;
import DJ.tinder.readService.model.skill.dto.SkillReadDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)

public class DeveloperReadDto {


    private Long id;

    private String firstName;
    private String lastName;
    private String description;
    private String profession;
    private List<AchievementReadDto> achievements;
    private List<SkillReadDto> skills;

}
