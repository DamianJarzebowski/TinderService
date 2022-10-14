package DJ.tinder.benefitTest;

import DJ.tinder.readService.model.benefit.dto.BenefitReadDto;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;

import static DJ.tinder.testMethods.CreateReadUpdateDelete.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BenefitControllerTest {

    public static final String BASE_URL = "/benefits";
    String baseUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()) + BASE_URL;
    }

    @Test
    void shouldThatCreateAndGetNewBenefitCorrect() {

        var benefit = create(baseUri, BenefitReadDto.class, new BenefitWriteDto()
                .setName("Test"));

        var location = baseUri + "/" + benefit.getId();

        var actual = read(location, BenefitReadDto.class);

        var expected = new BenefitReadDto()
                .setId(actual.getId())
                .setName("Test");

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThatCreateAndUpdateNewBenefitCorrect() {

        var benefit = create(baseUri, BenefitReadDto.class, new BenefitWriteDto()
                .setName("Test"));

        var locationCreatedEvent = baseUri + "/" + benefit.getId();

        var actual = update(locationCreatedEvent, BenefitReadDto.class, new BenefitWriteDto()
                .setName("NewTestName"));;

        var excepted = new BenefitReadDto()
                .setId(benefit.getId())
                .setName("NewTestName");

        Assertions.assertThat(actual).isEqualTo(excepted);
    }
}
