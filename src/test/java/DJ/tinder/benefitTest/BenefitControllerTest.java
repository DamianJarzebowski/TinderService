package DJ.tinder.benefitTest;

import DJ.tinder.readService.model.achievement.dto.AchievementReadDto;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
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

    public static final String BASE_URL = "/achievements";
    String baseUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()) + BASE_URL;
    }

    @Test
    void shouldThatCreateAndGetNewBenefitCorrect() {

        // Create new benefit
        var benefit = RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(new AchievementReadDto()
                        .setName("TestBenefit"))
                .when()
                .post(baseUri)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(AchievementReadDto.class);

        // Get created benefit
        var actual = RestAssured
                .given()
                .headers("Content-Type", ContentType.JSON)
                .get(baseUri + "/" + benefit.getId())
                .as(AchievementReadDto.class);

        // What is expected
        var expected = new AchievementReadDto()
                .setId(actual.getId())
                .setName("TestBenefit");

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThatCreateAndUpdateNewBenefitCorrect() {

        // Create new benefit
        var benefit = create(baseUri, AchievementReadDto.class, new AchievementWriteDto()
                .setName("Test"));

        // Update created benefit
        var locationCreatedEvent = baseUri + "/" + benefit.getId();
        var updatedAchievement = update(locationCreatedEvent, AchievementReadDto.class, new AchievementWriteDto()
                .setName("NewTestName"));

        // Set updated benefit as actual for assert
        var actual = read(locationCreatedEvent, AchievementReadDto.class);

        // Set date from updated as excepted
        var excepted = new AchievementReadDto()
                .setId(benefit.getId())
                .setName("NewTestName");

        Assertions.assertThat(actual).isEqualTo(excepted);
    }
}
