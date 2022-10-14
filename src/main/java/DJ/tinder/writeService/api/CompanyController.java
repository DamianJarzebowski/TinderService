package DJ.tinder.writeService.api;

import DJ.tinder.readService.model.company.dto.CompanyReadDto;
import DJ.tinder.readService.model.company.mapper.CompanyReadMapper;
import DJ.tinder.writeService.model.company.CompanyService;
import DJ.tinder.writeService.model.company.dto.CompanyWriteDto;

import DJ.tinder.writeService.model.company.dto.CompanyWriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    
    private final CompanyService companyService;
    private final CompanyReadMapper companyReadMapper;
    private final CompanyWriteMapper companyWriteMapper;

    @PostMapping
    public CompanyReadDto create(@RequestBody CompanyWriteDto companyWriteDto) {
        return companyReadMapper.toDto(
                companyService.create(
                        companyWriteMapper.toEntity(companyWriteDto)
                ));
    }

    @PutMapping("/{id}")
    public CompanyReadDto update(@PathVariable Long id, @RequestBody String name) {
        return companyReadMapper.toDto(
                companyService.update(id, name));
    }
}
