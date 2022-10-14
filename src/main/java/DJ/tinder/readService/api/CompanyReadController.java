package DJ.tinder.readService.api;

import DJ.tinder.readService.model.company.dto.CompanyReadDto;
import DJ.tinder.readService.model.company.CompanyService;
import DJ.tinder.readService.model.company.mapper.CompanyReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyReadController {
    
    private final CompanyService companyService;
    private final CompanyReadMapper companyReadMapper;

    @GetMapping
    public List<CompanyReadDto> findAll() {
        return companyReadMapper.toDto(
                companyService.findAll());
    }

    @GetMapping("/{id}")
    public CompanyReadDto findById(@PathVariable Long id) {
        return companyReadMapper.toDto(
                companyService.findById(id));
    }

}
