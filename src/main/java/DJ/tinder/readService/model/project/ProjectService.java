package DJ.tinder.readService.model.project;

import java.util.List;

public interface ProjectService {

    Project create(Project project);

    Project findById(Long id);

    Project findRandom(Long developerId);

    List<Project> findAll();

}
