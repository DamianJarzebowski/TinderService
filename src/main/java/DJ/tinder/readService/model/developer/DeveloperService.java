package DJ.tinder.readService.model.developer;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeveloperService {


    Developer findById(Long id);

    Developer findRandom(Long projectId);

    List<Developer> findAll();
}
