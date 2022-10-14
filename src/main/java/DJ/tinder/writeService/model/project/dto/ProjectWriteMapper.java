package DJ.tinder.writeService.model.project.dto;

import DJ.tinder.readService.model.company.mapper.CompanyReadToProjectMapper;
import DJ.tinder.readService.model.project.Project;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteMapper;
import DJ.tinder.writeService.model.skill.dto.SkillWriteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectWriteMapper {

    private final SkillWriteMapper skillWriteMapper;
    private final BenefitWriteMapper benefitWriteMapper;
    private final CompanyReadToProjectMapper companyReadToProjectMapper;

    public Project toEntity(ProjectWriteDto dto) {
        return new Project()
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setSkills(skillWriteMapper.toEntity(dto.getSkills()))
                .setBenefits(benefitWriteMapper.toEntity(dto.getBenefits()))
                .setCompany(companyReadToProjectMapper.toEntity(dto.getCompany()));
    }

    public List<Project> toEntity(List<ProjectWriteDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
