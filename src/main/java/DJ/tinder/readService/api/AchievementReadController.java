package DJ.tinder.readService.api;

import DJ.tinder.readService.model.achievement.AchievementService;
import DJ.tinder.readService.model.achievement.dto.AchievementReadDto;
import DJ.tinder.readService.model.achievement.dto.AchievementReadMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/achievements")
@RequiredArgsConstructor
public class AchievementReadController {

    private final AchievementService achievementService;
    private final AchievementReadMapper achievementReadMapper;

    @GetMapping
    public List<AchievementReadDto> findAll() {
        return achievementReadMapper.toDto(
                achievementService.findAll());
    }

    @GetMapping("/{id}")
    public AchievementReadDto findById(@PathVariable Long id) {
        return achievementReadMapper.toDto(
                achievementService.findById(id));
    }



}
