package DJ.tinder.writeService.model.project;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.notFound.NotFoundException;
import DJ.tinder.readService.model.benefit.Benefit;
import DJ.tinder.readService.model.project.Project;
import DJ.tinder.readService.model.project.ProjectRepository;
import DJ.tinder.readService.model.skill.Skill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImplWrite implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Project findRandom(Long developerId) {
        log.info(String.format("Downloading random project by devId: %d", developerId));
        List<Project> projects = projectRepository.getRandomProjects(developerId);
        if (projects.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND);
        } else {
            return projects.get(getRandomId(projects.size()-1));
        }
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
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
    public Project updateSkills(Long id, List<Skill> skills) {
        return projectRepository.findById(id)
                .map(projectFromDb -> {
                    projectFromDb.setSkills(skills);
                    return projectRepository.save(projectFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Project updateBenefits(Long id, List<Benefit> benefits) {
        return projectRepository.findById(id)
                .map(projectFromDb -> {
                    projectFromDb.setBenefits(benefits);
                    return projectRepository.save(projectFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    private int getRandomId(int max) {
        return new Random().nextInt(max);
    }
}
