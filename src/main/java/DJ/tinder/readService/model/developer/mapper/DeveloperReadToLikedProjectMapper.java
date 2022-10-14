package DJ.tinder.readService.model.developer.mapper;

import DJ.tinder.readService.model.achievement.dto.AchievementReadMapper;
import DJ.tinder.readService.model.developer.Developer;
import DJ.tinder.readService.model.developer.dto.DeveloperReadToLikedProjectDto;
import DJ.tinder.readService.model.skill.dto.SkillReadMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeveloperReadToLikedProjectMapper {

    private final AchievementReadMapper achievementReadMapper;
    private final SkillReadMapper skillReadMapper;

    public DeveloperReadToLikedProjectDto toDto(Developer developer) {
        return new DeveloperReadToLikedProjectDto()
                .setId(developer.getId())
                .setFirstName(developer.getFirstName())
                .setLastName(developer.getLastName())
                .setDescription(developer.getDescription())
                .setAchievements(achievementReadMapper.toDto(developer.getAchievements()))
                .setSkills(skillReadMapper.toDto(developer.getSkills()));
    }

    public List<DeveloperReadToLikedProjectDto> toDto(List<Developer> developerList) {
        return developerList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
