package DJ.tinder.writeService.model.project.dto;

import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProjectToCompanyWriteDto {

    private String name;
    private String description;
    private List<SkillWriteDto> skills;
    private List<BenefitWriteDto> benefits;
}
