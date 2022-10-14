package DJ.tinder.writeService.api;

import DJ.tinder.readService.model.skill.dto.SkillReadDto;
import DJ.tinder.readService.model.skill.dto.SkillReadMapper;
import DJ.tinder.writeService.model.skill.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;
    private final SkillReadMapper skillReadMapper;

    @PostMapping
    public SkillReadDto create(@RequestBody String name) {
        return skillReadMapper.toDto(
                skillService.create(name));
    }

    @PutMapping("/{id}")
    public SkillReadDto update(@PathVariable Long id, @RequestBody String name) {
        return skillReadMapper.toDto(
                skillService.update(id, name));
    }
}
