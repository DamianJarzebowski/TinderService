package DJ.tinder.companyTest;

import DJ.tinder.readService.model.company.dto.CompanyReadDto;
import DJ.tinder.readService.model.project.dto.ProjectReadToCompanyDto;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import DJ.tinder.writeService.model.company.dto.CompanyWriteDto;
import DJ.tinder.writeService.model.project.dto.ProjectToCompanyWriteDto;
import DJ.tinder.writeService.model.skill.dto.SkillWriteDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.net.URI;
import java.util.ArrayList;

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

        var listProjects  = new ArrayList<ProjectToCompanyWriteDto>();
        listProjects.add(new ProjectToCompanyWriteDto()
                .setName("ProjectOne")
                .setDescription("Sth")
                .setSkills(listSkills)
                .setBenefits(listBenefits)
        );

        var listProjectsToExpected = new ArrayList<ProjectReadToCompanyDto>();
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
                .setProjects(listProjectsToExpected);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThatCreateAndUpdateNewCompanyCorrect() {

        // Date to create

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

        var companyDate = new CompanyWriteDto()
                .setName("Company")
                .setProjects(listProjects);

        // Date to Update

        var listSkillsToUpdate = new ArrayList<SkillWriteDto>();
        listSkillsToUpdate.add(new SkillWriteDto().setName("Python"));
        listSkillsToUpdate.add(new SkillWriteDto().setName("SQL"));

        var listBenefitsToUpdate = new ArrayList<BenefitWriteDto>();
        listBenefitsToUpdate.add(new BenefitWriteDto().setName("Fruits"));
        listBenefitsToUpdate.add(new BenefitWriteDto().setName("Bowling"));

        var listProjectsToUpgrade = new ArrayList<ProjectToCompanyWriteDto>();
        listProjects.add(new ProjectToCompanyWriteDto()
                .setName("ProjectOneUpdate")
                .setDescription("SthUpdate")
                .setSkills(listSkillsToUpdate)
                .setBenefits(listBenefitsToUpdate)
        );

        var companyDateToUpgrade = new CompanyWriteDto()
                .setName("CompanyNameUpgraded")
                .setProjects(listProjectsToUpgrade);

        // Date to excepted

        var listProjectsToExcepted = new ArrayList<ProjectReadToCompanyDto>();
        listProjects.add(new ProjectToCompanyWriteDto()
                .setName("ProjectOneUpdate")
                .setDescription("SthUpdate")
                .setSkills(listSkillsToUpdate)
                .setBenefits(listBenefitsToUpdate)
        );

        // Main logic testing

        var company = create(baseUri, CompanyReadDto.class, companyDate);

        var locationCreatedEvent = baseUri + "/" + company.getId();

        var actual = update(locationCreatedEvent, CompanyReadDto.class, companyDateToUpgrade);

        var excepted = new CompanyReadDto()
                .setId(actual.getId())
                .setName("CompanyNameUpgraded")
                .setProjects(listProjectsToExcepted);

        Assertions.assertThat(actual).isEqualTo(excepted);
    }

}