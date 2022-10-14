package DJ.tinder.readService.model.benefit;

import java.util.List;

public interface BenefitService {

    List<Benefit> findAll();

    Benefit findById(Long id);
}
