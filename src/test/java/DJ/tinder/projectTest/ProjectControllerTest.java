package DJ.tinder.projectTest;

import DJ.tinder.readService.model.benefit.dto.BenefitReadDto;
import DJ.tinder.readService.model.company.dto.CompanyReadDto;
import DJ.tinder.readService.model.company.dto.CompanyReadToProjectDto;
import DJ.tinder.readService.model.project.dto.ProjectReadDto;
import DJ.tinder.readService.model.project.dto.ProjectReadToCompanyDto;
import DJ.tinder.readService.model.skill.dto.SkillReadDto;
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

import static DJ.tinder.testMethods.CreateReadUpdateDelete.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectControllerTest {

    String baseUri;
    String projectUri;
    String companyUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()).toString();
        companyUri = baseUri + "/companies";
        projectUri = baseUri + "/projects";
    }

    @Test
    void shouldThatCreateAndGetNewProjectCorrect() {

        // Create a company to assign the project

        var company = create(companyUri, CompanyReadDto.class, new CompanyWriteDto()
                .setName("Company")
                .setProjects(new ArrayList<>()));

        // Date for create project

        var listSkills = new ArrayList<SkillWriteDto>();
        listSkills.add(new SkillWriteDto().setName("Java"));
        listSkills.add(new SkillWriteDto().setName("GIT"));

        var listBenefits = new ArrayList<BenefitWriteDto>();
        listBenefits.add(new BenefitWriteDto().setName("Coffee"));
        listBenefits.add(new BenefitWriteDto().setName("MultiSport Card"));

        var projectDate = new ProjectWriteDto()
                .setName("ProjectOne")
                .setDescription("Sth")
                .setSkills(listSkills)
                .setBenefits(listBenefits)
                .setCompany(new CompanyReadToProjectDto()
                        .setId(company.getId())
                        .setName("Company"));

        // Create project

        var project = create(projectUri, ProjectReadDto.class, projectDate);

        // Build project location

        var location = projectUri + "/" + project.getId();

        // Read created project

        var actual = read(location, ProjectReadDto.class);

        // Data for excepted

        var listSkillsProjectExcepted = new ArrayList<SkillReadDto>();
        listSkillsProjectExcepted.add(new SkillReadDto()
                .setId(project.getSkills().get(0).getId())
                .setName("Java"));
        listSkillsProjectExcepted.add(new SkillReadDto()
                .setId(project.getSkills().get(1).getId())
                .setName("GIT"));

        var listBenefitsProjectExcepted = new ArrayList<BenefitReadDto>();
        listBenefitsProjectExcepted.add(new BenefitReadDto()
                .setId(project.getBenefits().get(0).getId())
                .setName("Coffee"));
        listBenefitsProjectExcepted.add(new BenefitReadDto()
                .setId(project.getBenefits().get(1).getId())
                .setName("MultiSport Card"));

        var expected = new ProjectReadDto()
                .setId(project.getId())
                .setName("ProjectOne")
                .setDescription("Sth")
                .setSkills(listSkillsProjectExcepted)
                .setBenefits(listBenefitsProjectExcepted)
                .setCompany(new CompanyReadToProjectDto()
                        .setId(company.getId())
                        .setName("Company"));

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldThatCreateAndUpdateNewProjectCorrect() {

    }


}
