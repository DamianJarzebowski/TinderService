package DJ.tinder.readService.model.project.mapper;

import DJ.tinder.readService.model.benefit.dto.BenefitReadMapper;
import DJ.tinder.readService.model.project.Project;
import DJ.tinder.readService.model.project.dto.ProjectReadToCompanyDto;
import DJ.tinder.readService.model.skill.dto.SkillReadMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProjectReadToCompanyMapper {

    private final SkillReadMapper skillReadMapper;
    private final BenefitReadMapper benefitReadMapper;

    public ProjectReadToCompanyDto toDto(Project project) {
        return new ProjectReadToCompanyDto()
                .setId(project.getId())
                .setName(project.getName())
                .setDescription(project.getDescription())
                .setSkills(skillReadMapper.toDto(project.getSkills()))
                .setBenefits(benefitReadMapper.toDto(project.getBenefits()));
    }

    public List<ProjectReadToCompanyDto> toDto(List<Project> projectList) {
        return projectList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
