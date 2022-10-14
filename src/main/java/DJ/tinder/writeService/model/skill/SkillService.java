package DJ.tinder.writeService.model.skill;

import DJ.tinder.readService.model.skill.Skill;

import java.util.List;

public interface SkillService {

    List<Skill> findAll();

    Skill findById(Long id);

    Skill create(String name);

    Skill update(Long id, String name);

}
