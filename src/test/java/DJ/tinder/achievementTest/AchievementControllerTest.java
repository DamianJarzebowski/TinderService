package DJ.tinder.achievementTest;

import DJ.tinder.readService.model.achievement.dto.AchievementReadDto;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;

import static DJ.tinder.testMethods.CreateReadUpdateDelete.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AchievementControllerTest {

    public static final String BASE_URL = "/achievements";
    String baseUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()) + BASE_URL;
    }

    @Test
    void shouldThatCreateAndGetNewAchievementCorrect() {

        var achievement = create(baseUri, AchievementReadDto.class, new AchievementWriteDto()
                .setName("Test"));

        var location = baseUri + "/" + achievement.getId();

        var actual = read(location, AchievementReadDto.class);

        var expected = new AchievementReadDto()
                .setId(actual.getId())
                .setName("Test");

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThatCreateAndUpdateNewAchievementCorrect() {

        var achievement = create(baseUri, AchievementReadDto.class, new AchievementWriteDto()
                .setName("Test"));

        var locationCreatedEvent = baseUri + "/" + achievement.getId();

        var actual = updatePut(locationCreatedEvent, AchievementReadDto.class, new AchievementWriteDto()
                .setName("NewTestName"));

        var excepted = new AchievementReadDto()
                .setId(achievement.getId())
                .setName("NewTestName");

        Assertions.assertThat(actual).isEqualTo(excepted);
    }
}


