package DJ.tinder.readService.model.company.mapper;

import DJ.tinder.readService.model.company.Company;
import DJ.tinder.readService.model.company.dto.CompanyReadToProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyReadToProjectMapper {

    public CompanyReadToProjectDto toDto(Company company) {
        return new CompanyReadToProjectDto()
                .setId(company.getId())
                .setName(company.getName());
    }

    public List<CompanyReadToProjectDto> toDto(List<Company> companyList) {
        return companyList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Company toEntity(CompanyReadToProjectDto dto) {
        return new Company()
                .setId(dto.getId())
                .setName(dto.getName());
    }

}
