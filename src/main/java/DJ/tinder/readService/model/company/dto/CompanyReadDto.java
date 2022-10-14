package DJ.tinder.readService.model.company.dto;

import DJ.tinder.readService.model.project.dto.ProjectReadToCompanyDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class CompanyReadDto {

    private Long id;
    private String name;
    private List<ProjectReadToCompanyDto> projects;


}
