package DJ.tinder.writeService.model.project;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.notFound.NotFoundException;
import DJ.tinder.readService.model.benefit.Benefit;
import DJ.tinder.readService.model.project.Project;
import DJ.tinder.readService.model.project.ProjectRepository;
import DJ.tinder.readService.model.skill.Skill;
import DJ.tinder.writeService.model.benefit.BenefitService;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import DJ.tinder.writeService.model.skill.SkillService;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImplWrite implements ProjectService {

    private final ProjectRepository projectRepository;
    private final BenefitService benefitService;
    private final SkillService skillService;

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateBasicInformation(Long id, Project project) {
        return projectRepository.findById(id)
                .map(projectFromDb -> {
                    projectFromDb.setName(project.getName());
                    projectFromDb.setDescription(project.getDescription());
                    return projectRepository.save(projectFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Project updateSkills(Long id, List<SkillWriteDto> dtoList) {
        List<Skill> skills = new ArrayList<>();
        for (SkillWriteDto dto : dtoList) {
            Skill skill = skillService.create(dto);
            skills.add(skill);
        }
        return projectRepository.findById(id)
                .map(projectFromDb -> {
                    projectFromDb.setSkills(skills);
                    return projectRepository.save(projectFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Project updateBenefits(Long id, List<BenefitWriteDto> dtoList) {
        List<Benefit> benefits = new ArrayList<>();
        for (BenefitWriteDto dto : dtoList) {
            Benefit benefit = benefitService.create(dto);
            benefits.add(benefit);
        }
        return projectRepository.findById(id)
                .map(projectFromDb -> {
                    projectFromDb.setBenefits(benefits);
                    return projectRepository.save(projectFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }
}
