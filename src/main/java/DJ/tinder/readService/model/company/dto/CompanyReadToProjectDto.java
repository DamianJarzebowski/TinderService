package DJ.tinder.readService.model.company.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompanyReadToProjectDto {

    private Long id;
    private String name;
    
}
