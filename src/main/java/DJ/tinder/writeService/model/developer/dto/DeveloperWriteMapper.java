package DJ.tinder.writeService.model.developer.dto;

import DJ.tinder.readService.model.developer.Developer;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteMapper;
import DJ.tinder.writeService.model.skill.dto.SkillWriteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeveloperWriteMapper {

    private final AchievementWriteMapper achievementWriteMapper;
    private final SkillWriteMapper skillWriteMapper;

    public Developer toEntity(DeveloperWriteDto dto) {
        return new Developer()
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setDescription(dto.getDescription())
                .setProfession(dto.getProfession())
                .setAchievements(achievementWriteMapper.toEntity(dto.getAchievements()))
                .setSkills(skillWriteMapper.toEntity(dto.getSkills()));
    }
}
