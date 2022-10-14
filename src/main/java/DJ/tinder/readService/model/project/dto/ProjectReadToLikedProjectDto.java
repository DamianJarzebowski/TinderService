package DJ.tinder.readService.model.project.dto;

import DJ.tinder.readService.model.benefit.dto.BenefitReadDto;
import DJ.tinder.readService.model.company.dto.CompanyReadToLikedProjectDto;
import DJ.tinder.readService.model.skill.dto.SkillReadDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProjectReadToLikedProjectDto {

    private Long id;
    private String name;
    private String description;
    private List<SkillReadDto> skills;
    private List<BenefitReadDto> benefits;
    private CompanyReadToLikedProjectDto company;

}
