package DJ.tinder.companyTest;

import DJ.tinder.readService.model.company.dto.CompanyReadDto;
import DJ.tinder.readService.model.project.Project;
import DJ.tinder.readService.model.project.dto.ProjectReadToCompanyDto;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import DJ.tinder.writeService.model.company.dto.CompanyWriteDto;
import DJ.tinder.writeService.model.project.dto.ProjectToCompanyWriteDto;
import DJ.tinder.writeService.model.project.dto.ProjectWriteDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    void shouldThatCreateAndGetNewCompanyCorrect() {

        var listSkills = new ArrayList<SkillWriteDto>();
        listSkills.add(new SkillWriteDto().setName("Java"));
        listSkills.add(new SkillWriteDto().setName("GIT"));

        var listBenefits = new ArrayList<BenefitWriteDto>();
        listBenefits.add(new BenefitWriteDto().setName("Coffee"));
        listBenefits.add(new BenefitWriteDto().setName("MultiSport Card"));

        var listProjects = new ArrayList<ProjectToCompanyWriteDto>();
        listProjects.add(new ProjectToCompanyWriteDto()
                .setName("ProjectOne")
                .setDescription("Sth")
                .setSkills(listSkills)
                .setBenefits(listBenefits)
        );

        var listProjects2 = new ArrayList<ProjectReadToCompanyDto>();
        listProjects.add(new ProjectToCompanyWriteDto()
                .setName("ProjectOne")
                .setDescription("Sth")
                .setSkills(listSkills)
                .setBenefits(listBenefits)
        );

        var companyDate = new CompanyWriteDto()
                .setName("Company")
                .setProjects(listProjects);

        var company = create(baseUri, CompanyReadDto.class, companyDate);

        var location = baseUri + "/" + company.getId();

        var actual = read(location, CompanyReadDto.class);

        var expected = new CompanyReadDto()
                .setId(actual.getId())
                .setName("Company")
                .setProjects(listProjects2);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    /*
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