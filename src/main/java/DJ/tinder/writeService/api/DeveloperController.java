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
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DeveloperReadDto create( @RequestBody DeveloperWriteDto dto) {
        return developerReadMapper.toDto(
                developerService.create(
                        developerWriteMapper.toEntity(dto)));
    }

    @PutMapping("/{id}/general")
    public DeveloperReadDto updatePersonalInformation(@PathVariable Long id, @RequestBody DeveloperWriteDto dto) {
        return developerReadMapper.toDto(
                developerService.updatePersonalInformation(id, developerWriteMapper.toEntity(dto)));
    }

    @PutMapping("/{id}/achievements")
    public DeveloperReadDto updateAchievements(@PathVariable Long id, @RequestBody List<AchievementWriteDto> dtoList) {
        return developerReadMapper.toDto(
                developerService.updateAchievements(id, dtoList));
    }

    @PutMapping("/{id}/skills")
    public DeveloperReadDto updateSkills(@PathVariable Long id, @RequestBody List<SkillWriteDto> dtoList) {
        return developerReadMapper.toDto(
                developerService.updateSkills(id, dtoList));
    }
}
