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

    @PostMapping
    public ProjectReadDto create(@RequestBody ProjectWriteDto projectWriteDto) {
        return projectReadMapper.toDto(
                projectService.create(
                        projectWriteMapper.toEntity(projectWriteDto)));
    }

    @PutMapping("/{id}/general")
    public ProjectReadDto updateBasicInformation(@PathVariable Long id, @RequestBody ProjectWriteDto projectWriteDto) {
        return projectReadMapper.toDto(
                projectService.updateBasicInformation(id, projectWriteMapper.toEntity(projectWriteDto)));
    }

    @PutMapping("/{id}/skills")
    public ProjectReadDto updateSkills(@PathVariable Long id, @RequestBody List<SkillWriteDto> skillWriteDtoList) {
        return projectReadMapper.toDto(
                projectService.updateSkills(id, skillWriteMapper.toEntity(skillWriteDtoList)));
    }

    @PutMapping("/{id}/benefits")
    public ProjectReadDto updateBenefits(@PathVariable Long id, @RequestBody List<BenefitWriteDto> benefitWriteDtoList) {
        return projectReadMapper.toDto(
                projectService.updateBenefits(id, benefitWriteMapper.toEntity(benefitWriteDtoList)));
    }

}
