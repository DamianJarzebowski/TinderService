package DJ.tinder.writeService.api;

import DJ.tinder.readService.model.benefit.dto.BenefitReadDto;
import DJ.tinder.readService.model.benefit.dto.BenefitReadMapper;
import DJ.tinder.writeService.model.benefit.BenefitService;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/benefits")
public class BenefitController {
    
    private final BenefitService benefitService;
    private final BenefitReadMapper benefitReadMapper;

    @PostMapping
    public BenefitReadDto create(@RequestBody BenefitWriteDto dto) {
        return benefitReadMapper.toDto(
                benefitService.create(dto));
    }

    @PutMapping("/{id}")
    public BenefitReadDto update(@PathVariable Long id, @RequestBody BenefitWriteDto dto) {
        return benefitReadMapper.toDto(
                benefitService.update(id, dto));
    }
    
}
