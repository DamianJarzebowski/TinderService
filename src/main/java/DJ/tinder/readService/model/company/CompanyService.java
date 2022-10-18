package DJ.tinder.readService.model.company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company findById(Long id);
}
