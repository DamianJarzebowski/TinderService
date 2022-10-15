package DJ.tinder.projectTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {

    public static final String BASE_URL = "/companies";
    String baseUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()) + BASE_URL;
    }

    @Test
    void shouldThatCreateAndGetNewProjectCorrect() {

    }

    @Test
    void shouldThatCreateAndUpdateNewProjectCorrect() {

    }

}
