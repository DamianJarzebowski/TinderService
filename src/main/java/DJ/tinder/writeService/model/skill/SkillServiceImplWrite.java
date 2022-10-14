package DJ.tinder.writeService.model.skill;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.badRequest.BadRequestException;
import DJ.tinder.exception.notFound.NotFoundException;

import DJ.tinder.readService.model.skill.Skill;
import DJ.tinder.readService.model.skill.SkillRepository;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImplWrite implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public Skill create(SkillWriteDto dto) {
        validateName(dto.getName());
        return skillRepository.save(Skill.builder()
                .name(dto.getName())
                .build());
    }

    @Override
    public Skill update(Long id, SkillWriteDto dto) {
        validateName(dto.getName());
        return skillRepository.findById(id)
                .map(skillFromDb -> {
                    skillFromDb.setName(dto.getName());
                    return skillRepository.save(skillFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    private void validateName(String name) {
        if(name.length() < 3) throw new BadRequestException(ErrorMessage.BAD_REQUEST);
    }
}
