package DJ.tinder.readService.api;

import DJ.tinder.readService.model.benefit.dto.BenefitReadDto;
import DJ.tinder.readService.model.benefit.BenefitService;
import DJ.tinder.readService.model.benefit.dto.BenefitReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/benefits")
public class BenefitReadController {
    
    private final BenefitService benefitService;
    private final BenefitReadMapper benefitReadMapper;

    @GetMapping
    public List<BenefitReadDto> findAll() {
        return benefitReadMapper.toDto(
                benefitService.findAll());
    }

    @GetMapping("/{id}")
    public BenefitReadDto findById(@PathVariable Long id) {
        return benefitReadMapper.toDto(
                benefitService.findById(id));
    }

}
