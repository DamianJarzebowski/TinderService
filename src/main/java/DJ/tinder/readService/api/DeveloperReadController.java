package DJ.tinder.readService.api;

import DJ.tinder.readService.model.developer.dto.DeveloperReadDto;
import DJ.tinder.readService.model.developer.mapper.DeveloperReadMapper;
import DJ.tinder.readService.model.developer.DeveloperService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/developers")
public class DeveloperReadController {

    private final DeveloperService developerService;
    private final DeveloperReadMapper developerReadMapper;

    @GetMapping
    public List<DeveloperReadDto> findAll() {
        return developerReadMapper.toDto(
                developerService.findAll());
    }

    @GetMapping("/{id}")
    public DeveloperReadDto findById(@PathVariable Long id) {
        return developerReadMapper.toDto(
                developerService.findById(id));
    }

    @GetMapping("/random/{projectId}")
    public DeveloperReadDto findRandom(@PathVariable Long projectId) {
        return developerReadMapper.toDto(
                developerService.findRandom(projectId));
    }

}
