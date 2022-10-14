package DJ.tinder.writeService.model.benefit;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.badRequest.BadRequestException;
import DJ.tinder.exception.notFound.NotFoundException;
import DJ.tinder.readService.model.benefit.Benefit;
import DJ.tinder.readService.model.benefit.BenefitRepository;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BenefitServiceImplWrite implements BenefitService {

    private final BenefitRepository benefitRepository;

    @Override
    public Benefit create(BenefitWriteDto dto) {
        log.info(String.format("Creating benefit: %s", dto));
        validateName(dto.getName());
        return benefitRepository.save(
                Benefit.builder()
                        .name(dto.getName())
                        .build()
        );
    }

    @Override
    public Benefit update(Long id, BenefitWriteDto dto) {
        log.info(String.format("Updating benefit: %s , id: %d", dto, id));
        validateName(dto.getName());
        return benefitRepository.findById(id)
                .map(benefitFromDb -> {
                    benefitFromDb.setName(dto.getName());
                    return benefitRepository.save(benefitFromDb);
                }).orElseThrow(() -> {
                    log.error(String.format("Benefit id: %d does not exists", id));
                    return new NotFoundException(ErrorMessage.NOT_FOUND);
                });
    }

    private void validateName(String name) {
        if(name.length() < 3) {
            log.error(String.format("Invalid benefit name %s", name));
            throw new BadRequestException(ErrorMessage.BAD_REQUEST);
        }
    }
}
