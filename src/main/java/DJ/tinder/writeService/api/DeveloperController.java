package DJ.tinder.writeService.api;

import DJ.tinder.readService.model.developer.dto.DeveloperReadDto;
import DJ.tinder.readService.model.developer.mapper.DeveloperReadMapper;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteMapper;
import DJ.tinder.writeService.model.developer.DeveloperService;
import DJ.tinder.writeService.model.developer.dto.DeveloperWriteDto;
import DJ.tinder.writeService.model.developer.dto.DeveloperWriteMapper;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperService developerService;
    private final DeveloperReadMapper developerReadMapper;
    private final DeveloperWriteMapper developerWriteMapper;
    private final AchievementWriteMapper achievementWriteMapper;
    private final SkillWriteMapper skillWriteMapper;

    @PostMapping
    public DeveloperReadDto create( @RequestBody DeveloperWriteDto developerWriteDto) {
        return developerReadMapper.toDto(
                developerService.create(
                        developerWriteMapper.toEntity(developerWriteDto)));
    }

    @PutMapping("/{id}/general")
    public DeveloperReadDto updatePersonalInformation(@PathVariable Long id, @RequestBody DeveloperWriteDto developerWriteDto) {
        return developerReadMapper.toDto(
                developerService.updatePersonalInformation(id, developerWriteMapper.toEntity(developerWriteDto)));
    }

    @PutMapping("/{id}/achievements")
    public DeveloperReadDto updateAchievements(@PathVariable Long id, @RequestBody List<AchievementWriteDto> achievementWriteDtoList) {
        return developerReadMapper.toDto(
                developerService.updateAchievements(id, achievementWriteMapper.toEntity(achievementWriteDtoList)));
    }

    @PutMapping("/{id}/skills")
    public DeveloperReadDto updateSkills(@PathVariable Long id, @RequestBody List<SkillWriteDto> skillWriteDtoList) {
        return developerReadMapper.toDto(
                developerService.updateSkills(id, skillWriteMapper.toEntity(skillWriteDtoList)));
    }
}
