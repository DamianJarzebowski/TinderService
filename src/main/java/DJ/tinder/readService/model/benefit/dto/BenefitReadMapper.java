package DJ.tinder.readService.model.benefit.dto;

import DJ.tinder.readService.model.benefit.Benefit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BenefitReadMapper {

    public BenefitReadDto toDto(Benefit benefit) {
        return new BenefitReadDto()
                .setId(benefit.getId())
                .setName(benefit.getName());
    }

    public List<BenefitReadDto> toDto(List<Benefit> benefitList) {
        return benefitList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
