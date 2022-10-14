package DJ.tinder.writeService.model.company;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.badRequest.BadRequestException;
import DJ.tinder.exception.notFound.NotFoundException;
import DJ.tinder.readService.model.company.Company;
import DJ.tinder.readService.model.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImplWrite implements CompanyService{

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findAll() {
        log.info("Downloading all companies");
        return companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        log.info(String.format("Downloading company id: %d", id));
        return companyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(String.format("Company id: %d does not exists", id));
                    return new NotFoundException(ErrorMessage.NOT_FOUND);
                });
    }

    @Override
    public Company create(Company company) {
        log.info(String.format("Creating company %s", company.toString()));
        return companyRepository.save(company);
    }

    @Override
    public Company update(Long id, String name) {
        validateName(name);
        return companyRepository.findById(id)
                .map(companyFromDb -> {
                    companyFromDb.setName(name);
                    return companyRepository.save(companyFromDb);
                }).orElseThrow(() -> {
                    log.error(String.format("Company id: %d does not exists", id));
                    return new NotFoundException(ErrorMessage.NOT_FOUND);
                });
    }

    private void validateName(String name) {
        if(name.length() < 3) {
            log.error(String.format("Invalid company name %s", name));
            throw new BadRequestException(ErrorMessage.BAD_REQUEST);
        }
    }
}
