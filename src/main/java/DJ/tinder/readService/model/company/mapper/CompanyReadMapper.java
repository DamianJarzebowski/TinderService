package DJ.tinder.readService.model.company.mapper;

import DJ.tinder.readService.model.company.Company;
import DJ.tinder.readService.model.company.dto.CompanyReadDto;
import DJ.tinder.readService.model.project.mapper.ProjectReadMapper;
import DJ.tinder.readService.model.project.mapper.ProjectReadToCompanyMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyReadMapper {

    private final ProjectReadToCompanyMapper projectReadToCompanyMapper;
    private final ProjectReadMapper projectReadMapper;

    public CompanyReadDto toDto(Company company) {
        return new CompanyReadDto()
                .setId(company.getId())
                .setName(company.getName())
                .setProjects(projectReadToCompanyMapper.toDto(company.getProjects()));
    }

    public List<CompanyReadDto> toDto(List<Company> companyList) {
        return companyList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}
