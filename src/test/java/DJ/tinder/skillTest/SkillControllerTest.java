package DJ.tinder.skillTest;

import DJ.tinder.readService.model.skill.dto.SkillReadDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;

import static DJ.tinder.testMethods.CreateReadUpdateDelete.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SkillControllerTest {

    public static final String BASE_URL = "/skills";
    String baseUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()) + BASE_URL;
    }

    @Test
    void shouldThatCreateAndGetNewSkillCorrect() {

        var achievement = create(baseUri, SkillReadDto.class, new SkillWriteDto()
                .setName("Test"));

        var location = baseUri + "/" + achievement.getId();

        var actual = read(location, SkillReadDto.class);

        var expected = new SkillReadDto()
                .setId(actual.getId())
                .setName("Test");

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThatCreateAndUpdateNewSkillCorrect() {

        var skill = create(baseUri, SkillReadDto.class, new SkillWriteDto()
                .setName("Test"));

        var locationCreatedEvent = baseUri + "/" + skill.getId();

        var actual = updatePut(locationCreatedEvent, SkillReadDto.class, new SkillWriteDto()
                .setName("NewTestName"));

        var excepted = new SkillReadDto()
                .setId(skill.getId())
                .setName("NewTestName");

        Assertions.assertThat(actual).isEqualTo(excepted);
    }
}