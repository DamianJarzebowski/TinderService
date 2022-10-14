package DJ.tinder.readService.model.developer;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.notFound.NotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeveloperServiceImpl implements DeveloperService{

    private final DeveloperRepository developerRepository;

    @Override
    public Developer findById(Long id) {
        log.info(String.format("Downloading developer by id: %d", id));
        return developerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Developer findRandom(Long projectId) {
        log.info(String.format("Downloading random developer by projectId: %d", projectId));
        List<Developer> developers = developerRepository.getRandomDevelopers(projectId);
        if(developers.isEmpty()) {
            throw new NotFoundException(ErrorMessage.NOT_FOUND);
        } else {
            return developers.get(getRandomId(developers.size() - 1));
        }
    }

    @Override
    public List<Developer> findAll() {
        log.info("Downloading all developers");
        return developerRepository.findAll();
    }

    private int getRandomId(int max) {
        return new Random().nextInt(max);
    }

}
