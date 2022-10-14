package DJ.tinder.readService.model.project.mapper;

import DJ.tinder.readService.model.benefit.dto.BenefitReadMapper;
import DJ.tinder.readService.model.company.mapper.CompanyReadToLikeProjectMapper;
import DJ.tinder.readService.model.project.Project;
import DJ.tinder.readService.model.project.dto.ProjectReadToLikedProjectDto;
import DJ.tinder.readService.model.skill.dto.SkillReadMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProjectReadToLikedProjectMapper {

    private final SkillReadMapper skillReadMapper;
    private final BenefitReadMapper benefitReadMapper;
    private final CompanyReadToLikeProjectMapper companyReadToLikeProjectMapper;

    public ProjectReadToLikedProjectDto toDto(Project project) {
        return new ProjectReadToLikedProjectDto()
                .setId(project.getId())
                .setName(project.getName())
                .setDescription(project.getDescription())
                .setSkills(skillReadMapper.toDto(project.getSkills()))
                .setBenefits(benefitReadMapper.toDto(project.getBenefits()))
                .setCompany(companyReadToLikeProjectMapper.toDto(project.getCompany()));

    }

}
