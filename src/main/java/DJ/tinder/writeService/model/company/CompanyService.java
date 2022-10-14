package DJ.tinder.writeService.model.company;

import DJ.tinder.readService.model.company.Company;
import DJ.tinder.writeService.model.company.dto.CompanyWriteDto;

import java.util.List;

public interface CompanyService {

    Company create(CompanyWriteDto dto);

    Company update(Long id, CompanyWriteDto dto);
}
