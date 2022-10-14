package DJ.tinder.writeService.model.company;

import DJ.tinder.exception.ErrorMessage;
import DJ.tinder.exception.badRequest.BadRequestException;
import DJ.tinder.exception.notFound.NotFoundException;
import DJ.tinder.readService.model.company.Company;
import DJ.tinder.readService.model.company.CompanyRepository;
import DJ.tinder.writeService.model.company.dto.CompanyWriteDto;
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
    public Company create(CompanyWriteDto dto) {
        log.info(String.format("Creating company %s", dto.toString()));
        return companyRepository.save(Company.builder()
                .name(dto.getName())
                .build());
    }

    @Override
    public Company update(Long id, CompanyWriteDto dto) {
        validateName(dto.getName());
        return companyRepository.findById(id)
                .map(companyFromDb -> {
                    companyFromDb.setName(dto.getName());
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
