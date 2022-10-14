package DJ.tinder.writeService.model.company.dto;

import DJ.tinder.readService.model.company.Company;
import DJ.tinder.writeService.model.project.dto.ProjectWriteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyWriteMapper {

    private final ProjectWriteMapper projectWriteMapper;

    public Company toEntity(CompanyWriteDto dto) {
        return new Company()
                .setName(dto.getName())
                .setProjects(projectWriteMapper.toEntity(dto.getProjects()));
    }

}
