package DJ.tinder.writeService.model.achievement.dto;

import DJ.tinder.readService.model.achievement.Achievement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementWriteMapper {

    public Achievement toEntity(AchievementWriteDto dto) {
        return new Achievement()
                .setName(dto.getName());
    }

    public List<Achievement> toEntity(List<AchievementWriteDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
