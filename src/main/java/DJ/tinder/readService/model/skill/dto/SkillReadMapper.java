package DJ.tinder.readService.model.skill.dto;

import DJ.tinder.readService.model.skill.Skill;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillReadMapper {

    public SkillReadDto toDto(Skill skill) {
        return new SkillReadDto()
                .setId(skill.getId())
                .setName(skill.getName());
    }

    public List<SkillReadDto> toDto(List<Skill> skillList) {
        return skillList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Skill toEntity(SkillWriteDto skillWriteDto) {
        return new Skill()
                .setName(skillWriteDto.getName());
    }

    public List<Skill> toEntity(List<SkillWriteDto> skillWriteDtoList) {
        return skillWriteDtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
