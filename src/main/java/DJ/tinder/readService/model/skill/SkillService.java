package DJ.tinder.readService.model.skill;

import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;

import java.util.List;

public interface SkillService {

    List<Skill> findAll();

    Skill findById(Long id);

}
