package DJ.tinder.writeService.model.project.dto;

import DJ.tinder.readService.model.project.Project;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteMapper;
import DJ.tinder.writeService.model.skill.dto.SkillWriteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProjectToCompanyMapper {

    private final SkillWriteMapper skillWriteMapper;
    private final BenefitWriteMapper benefitWriteMapper;

    public Project toEntity(ProjectToCompanyWriteDto dto) {
        return new Project()
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setSkills(skillWriteMapper.toEntity(dto.getSkills()))
                .setBenefits(benefitWriteMapper.toEntity(dto.getBenefits()));
    }

    public List<Project> toEntity(List<ProjectToCompanyWriteDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }



}
