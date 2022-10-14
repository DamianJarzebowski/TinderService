package DJ.tinder.writeService.model.company.dto;

import DJ.tinder.writeService.model.project.dto.ProjectWriteDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CompanyWriteDto {

    private String name;
    private List<ProjectWriteDto> projects;
}
