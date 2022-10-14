package DJ.tinder.readService.model.company.mapper;

import DJ.tinder.readService.model.company.Company;
import DJ.tinder.readService.model.company.dto.CompanyReadToLikedProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyReadToLikeProjectMapper {

    public CompanyReadToLikedProjectDto toDto(Company company) {
        return new CompanyReadToLikedProjectDto()
                .setId(company.getId())
                .setName(company.getName());
    }

    public List<CompanyReadToLikedProjectDto> toDto(List<Company> companyList) {
        return companyList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
