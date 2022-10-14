package DJ.tinder.writeService.model.project.dto;

import DJ.tinder.readService.model.company.dto.CompanyReadToProjectDto;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProjectWriteDto {

    private String name;
    private String description;
    private List<SkillWriteDto> skills;
    private List<BenefitWriteDto> benefits;
    private CompanyReadToProjectDto company;

}
