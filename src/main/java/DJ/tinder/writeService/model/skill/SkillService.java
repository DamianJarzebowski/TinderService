package DJ.tinder.writeService.model.skill;

import DJ.tinder.readService.model.skill.Skill;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;

import java.util.List;

public interface SkillService {

    Skill create(SkillWriteDto dto);

    Skill update(Long id, SkillWriteDto dto);

}
