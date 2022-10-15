package DJ.tinder.writeService.model.company.dto;

import DJ.tinder.readService.model.company.Company;
import DJ.tinder.writeService.model.project.dto.ProjectToCompanyMapper;
import DJ.tinder.writeService.model.project.dto.ProjectWriteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyWriteMapper {

    private final ProjectToCompanyMapper projectToCompanyMapper;

    public Company toEntity(CompanyWriteDto dto) {
        return new Company()
                .setName(dto.getName())
                .setProjects(projectToCompanyMapper.toEntity(dto.getProjects()));
    }

}
