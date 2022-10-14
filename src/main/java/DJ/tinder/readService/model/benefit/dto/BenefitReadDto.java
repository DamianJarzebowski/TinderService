package DJ.tinder.readService.model.benefit.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BenefitReadDto {

    private Long id;
    private String name;
}
