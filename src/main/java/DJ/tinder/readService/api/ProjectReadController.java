package DJ.tinder.readService.api;

import DJ.tinder.readService.model.project.mapper.ProjectReadMapper;
import DJ.tinder.readService.model.project.ProjectService;
import DJ.tinder.readService.model.project.dto.ProjectReadDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectReadController {

    private final ProjectService projectService;
    private final ProjectReadMapper projectReadMapper;

    @GetMapping
    public List<ProjectReadDto> findAll() {
        return projectReadMapper.toDto(
                projectService.findAll());
    }

    @GetMapping("/{id}")
    public ProjectReadDto findById(@PathVariable Long id) {
        return projectReadMapper.toDto(
                projectService.findById(id));
    }

    @GetMapping("/random/{developerId}")
    public ProjectReadDto random(@PathVariable Long developerId) {
        return projectReadMapper.toDto(
                projectService.findRandom(developerId));
    }

}
