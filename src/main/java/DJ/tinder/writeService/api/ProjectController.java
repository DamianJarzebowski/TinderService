package DJ.tinder.writeService.api;

import DJ.tinder.readService.model.project.dto.ProjectReadDto;
import DJ.tinder.readService.model.project.mapper.ProjectReadMapper;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteMapper;
import DJ.tinder.writeService.model.project.ProjectService;

import DJ.tinder.writeService.model.project.dto.ProjectWriteDto;
import DJ.tinder.writeService.model.project.dto.ProjectWriteMapper;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectReadMapper projectReadMapper;
    private final ProjectWriteMapper projectWriteMapper;
    private final SkillWriteMapper skillWriteMapper;
    private final BenefitWriteMapper benefitWriteMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProjectReadDto create(@RequestBody ProjectWriteDto projectWriteDto) {
        return projectReadMapper.toDto(
                projectService.create(
                        projectWriteMapper.toEntity(projectWriteDto)));
    }

    @PutMapping("/{id}/general")
    public ProjectReadDto updateBasicInformation(@PathVariable Long id, @RequestBody ProjectWriteDto dto) {
        return projectReadMapper.toDto(
                projectService.updateBasicInformation(id, projectWriteMapper.toEntity(dto)));
    }

    @PutMapping("/{id}/skills")
    public ProjectReadDto updateSkills(@PathVariable Long id, @RequestBody List<SkillWriteDto> dtoList) {
        return projectReadMapper.toDto(
                projectService.updateSkills(id, skillWriteMapper.toEntity(dtoList)));
    }

    @PutMapping("/{id}/benefits")
    public ProjectReadDto updateBenefits(@PathVariable Long id, @RequestBody List<BenefitWriteDto> dtoList) {
        return projectReadMapper.toDto(
                projectService.updateBenefits(id, benefitWriteMapper.toEntity(dtoList)));
    }

}
