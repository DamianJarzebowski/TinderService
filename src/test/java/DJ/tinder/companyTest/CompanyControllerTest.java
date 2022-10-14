package DJ.tinder.companyTest;

import DJ.tinder.readService.model.company.dto.CompanyReadDto;
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
public class CompanyControllerTest {

    public static final String BASE_URL = "/companies";
    String baseUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()) + BASE_URL;
    }

    /*
    @Test
    void shouldThatCreateAndGetNewCompanyCorrect() {

        var company = create(baseUri, CompanyReadDto.class, new AchievementWriteDto()
                .setName("Test"));

        var location = baseUri + "/" + company.getId();

        var actual = read(location, CompanyReadDto.class);

        var expected = new CompanyReadDto()
                .setId(actual.getId())
                .setName("Test");

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThatCreateAndUpdateNewCompanyCorrect() {

        var company = create(baseUri, CompanyReadDto.class, new AchievementWriteDto()
                .setName("Test"));

        var locationCreatedEvent = baseUri + "/" + company.getId();

        var actual = update(locationCreatedEvent, CompanyReadDto.class, new AchievementWriteDto()
                .setName("NewTestName"));
        ;

        var excepted = new CompanyReadDto()
                .setId(company.getId())
                .setName("NewTestName");

        Assertions.assertThat(actual).isEqualTo(excepted);
    }

     */
}