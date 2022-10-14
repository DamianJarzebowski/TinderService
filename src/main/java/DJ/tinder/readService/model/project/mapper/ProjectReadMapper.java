package DJ.tinder.readService.model.project.mapper;

import DJ.tinder.readService.model.benefit.dto.BenefitReadMapper;
import DJ.tinder.readService.model.company.mapper.CompanyReadToProjectMapper;
import DJ.tinder.readService.model.project.Project;
import DJ.tinder.readService.model.project.dto.ProjectReadDto;
import DJ.tinder.readService.model.skill.dto.SkillReadMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProjectReadMapper {

    private final SkillReadMapper skillReadMapper;
    private final BenefitReadMapper benefitReadMapper;
    private final CompanyReadToProjectMapper companyReadToProjectMapper;

    public ProjectReadDto toDto(Project project) {
        return new ProjectReadDto()
                .setId(project.getId())
                .setName(project.getName())
                .setDescription(project.getDescription())
                .setSkills(skillReadMapper.toDto(project.getSkills()))
                .setBenefits(benefitReadMapper.toDto(project.getBenefits()))
                .setCompany(companyReadToProjectMapper.toDto(project.getCompany()));
    }

    public List<ProjectReadDto> toDto(List<Project> projectList) {
        return projectList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
