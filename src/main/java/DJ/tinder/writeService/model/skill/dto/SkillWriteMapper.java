package DJ.tinder.writeService.model.skill.dto;

import DJ.tinder.readService.model.skill.Skill;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillWriteMapper {

    public Skill toEntity(SkillWriteDto dto) {
        return new Skill()
                .setName(dto.getName());
    }

    public List<Skill> toEntity(List<SkillWriteDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
