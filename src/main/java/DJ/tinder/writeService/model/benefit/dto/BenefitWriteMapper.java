package DJ.tinder.writeService.model.benefit.dto;

import DJ.tinder.readService.model.benefit.Benefit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BenefitWriteMapper {

    public Benefit toEntity(BenefitWriteDto dto) {
        return new Benefit()
                .setName(dto.getName());
    }

    public List<Benefit> toEntity(List<BenefitWriteDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }


}
