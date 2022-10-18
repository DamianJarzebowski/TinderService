package DJ.tinder.writeService.api;

import DJ.tinder.readService.model.skill.dto.SkillReadDto;
import DJ.tinder.readService.model.skill.dto.SkillReadMapper;
import DJ.tinder.writeService.model.skill.SkillService;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;
    private final SkillReadMapper skillReadMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillReadDto create(@RequestBody SkillWriteDto dto) {
        return skillReadMapper.toDto(
                skillService.create(dto));
    }

    @PutMapping("/{id}")
    public SkillReadDto update(@PathVariable Long id, @RequestBody SkillWriteDto dto) {
        return skillReadMapper.toDto(
                skillService.update(id, dto));
    }
}
