package DJ.tinder.readService.model.developer.mapper;

import DJ.tinder.readService.model.achievement.dto.AchievementReadMapper;
import DJ.tinder.readService.model.developer.Developer;
import DJ.tinder.readService.model.developer.dto.DeveloperReadDto;
import DJ.tinder.readService.model.skill.dto.SkillReadMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DeveloperReadMapper {

    private final AchievementReadMapper achievementReadMapper;
    private final SkillReadMapper skillReadMapper;

    public DeveloperReadDto toDto(Developer developer) {
        return new DeveloperReadDto()
                .setId(developer.getId())
                .setFirstName(developer.getFirstName())
                .setLastName(developer.getLastName())
                .setDescription(developer.getDescription())
                .setProfession(developer.getProfession())
                .setAchievements(achievementReadMapper.toDto(developer.getAchievements()))
                .setSkills(skillReadMapper.toDto(developer.getSkills()));
    }

    public List<DeveloperReadDto> toDto(List<Developer> developerList) {
        return developerList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
