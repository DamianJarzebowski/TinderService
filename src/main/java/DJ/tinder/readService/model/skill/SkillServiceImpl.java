package DJ.tinder.readService.model.skill;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.badRequest.BadRequestException;
import DJ.tinder.exception.notFound.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    @Override
    public Skill create(String name) {
        validateName(name);
        return skillRepository.save(Skill.builder()
                .name(name)
                .build());
    }

    @Override
    public Skill update(Long id, String name) {
        validateName(name);
        return skillRepository.findById(id)
                .map(skillFromDb -> {
                    skillFromDb.setName(name);
                    return skillRepository.save(skillFromDb);
                }).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    private void validateName(String name) {
        if(name.length() < 3) throw new BadRequestException(ErrorMessage.BAD_REQUEST);
    }
}
