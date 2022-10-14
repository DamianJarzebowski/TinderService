package DJ.tinder.writeService.model.benefit;

import DJ.tinder.readService.model.benefit.Benefit;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;

import java.util.List;

public interface BenefitService {

    Benefit create(BenefitWriteDto dto);

    Benefit update(Long id, BenefitWriteDto dto);

}
