package DJ.tinder.writeService.model.project;

import DJ.tinder.readService.model.benefit.Benefit;
import DJ.tinder.readService.model.project.Project;
import DJ.tinder.readService.model.skill.Skill;

import java.util.List;

public interface ProjectService {

    Project create(Project project);

    Project findById(Long id);

    Project findRandom(Long developerId);

    List<Project> findAll();

    Project updateBasicInformation(Long id, Project project);

    Project updateSkills(Long id, List<Skill> skills);

    Project updateBenefits(Long id, List<Benefit> benefits);

}
