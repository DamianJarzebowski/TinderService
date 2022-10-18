package DJ.tinder.readService.model.company;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.badRequest.BadRequestException;
import DJ.tinder.exception.notFound.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        log.info("Downloading all companies");
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        log.info(String.format("Downloading company id: %d", id));
        log.info("Downloading company id: {}", id);

        return companyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(String.format("Company id: %d does not exists", id));
                    return new NotFoundException(ErrorMessage.NOT_FOUND);
                });
    }

}
