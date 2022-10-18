package DJ.tinder.writeService.model.project;

import DJ.tinder.readService.model.benefit.Benefit;
import DJ.tinder.readService.model.project.Project;
import DJ.tinder.readService.model.skill.Skill;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;

import java.util.List;

public interface ProjectService {

    Project create(Project project);

    Project updateBasicInformation(Long id, Project project);

    Project updateSkills(Long id, List<SkillWriteDto> dtoList);

    Project updateBenefits(Long id, List<BenefitWriteDto> dtoList);

}
