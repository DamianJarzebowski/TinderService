package DJ.tinder.readService.model.benefit;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.notFound.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BenefitServiceImpl implements BenefitService {

    private final BenefitRepository benefitRepository;

    @Override
    public List<Benefit> findAll() {
        log.info("Downloading all benfits");
        return benefitRepository.findAll();
    }

    @Override
    public Benefit findById(Long id) {
        log.info(String.format("Downloading benefit id: %d", id));
        return benefitRepository.findById(id)
                .orElseThrow(() -> {
            log.error(String.format("Benefit id: %d does not exists", id));
            return new NotFoundException(ErrorMessage.NOT_FOUND);
        });
    }

}
