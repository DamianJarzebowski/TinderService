package DJ.tinder.readService.api;

import DJ.tinder.readService.model.skill.dto.SkillReadDto;
import DJ.tinder.readService.model.skill.SkillService;
import DJ.tinder.readService.model.skill.dto.SkillReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillReadController {

    private final SkillService skillService;
    private final SkillReadMapper skillReadMapper;

    @GetMapping
    public List<SkillReadDto> findAll() {
        return skillReadMapper.toDto(
                skillService.findAll());
    }

    @GetMapping("/{id}")
    public SkillReadDto findById(@PathVariable Long id) {
        return skillReadMapper.toDto(
                skillService.findById(id));
    }

}
