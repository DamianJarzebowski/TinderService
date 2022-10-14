package DJ.tinder.readService.model.skill.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class SkillReadDto {

    private Long id;
    private String name;

}
