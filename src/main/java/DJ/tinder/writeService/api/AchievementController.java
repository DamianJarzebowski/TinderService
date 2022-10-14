package DJ.tinder.writeService.api;

import DJ.tinder.readService.model.achievement.dto.AchievementReadDto;
import DJ.tinder.readService.model.achievement.dto.AchievementReadMapper;
import DJ.tinder.writeService.model.achievement.AchievementService;

import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
public class AchievementController {

    private final AchievementService achievementService;
    private final AchievementReadMapper achievementReadMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AchievementReadDto create(@RequestBody AchievementWriteDto dto) {
        return achievementReadMapper.toDto(
                achievementService.create(dto));
    }

    @PutMapping("/{id}")
    public AchievementReadDto update(@PathVariable Long id, @RequestBody AchievementWriteDto dto) {
        return achievementReadMapper.toDto(
                achievementService.update(id, dto));
    }

}
