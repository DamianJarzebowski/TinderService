package DJ.tinder.projectTest;

import DJ.tinder.readService.model.benefit.dto.BenefitReadDto;
import DJ.tinder.readService.model.company.dto.CompanyReadDto;
import DJ.tinder.readService.model.company.dto.CompanyReadToProjectDto;
import DJ.tinder.readService.model.project.dto.ProjectReadDto;
import DJ.tinder.readService.model.skill.dto.SkillReadDto;
import DJ.tinder.writeService.model.benefit.dto.BenefitWriteDto;
import DJ.tinder.writeService.model.company.dto.CompanyWriteDto;
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
    void shouldThatCreateAndUpdateNewProjectCorrectBasicInformation() {

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

        var location = projectUri + "/" + project.getId() + "/general";

        // Date for update project

        var projectDataToUpgrade = new ProjectWriteDto()
                .setName("ProjectOneUpgrade")
                .setDescription("SthUpgrade")
                .setSkills(projectDate.getSkills())
                .setBenefits(projectDate.getBenefits())
                .setCompany(projectDate.getCompany());

        // Update basic information in project

        var actual = updatePut(location, ProjectReadDto.class, projectDataToUpgrade);

        // Excepted data

        var excepted = new ProjectReadDto()
                .setId(actual.getId())
                .setName("ProjectOneUpgrade")
                .setDescription("SthUpgrade")
                .setSkills(actual.getSkills())
                .setBenefits(actual.getBenefits())
                .setCompany(actual.getCompany());

        Assertions.assertThat(actual).isEqualTo(excepted);
    }

    @Test
    void shouldThatCreateAndUpdateBenefitsNewProjectCorrect() {

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

        var location = projectUri + "/" + project.getId() + "/benefits";

        // Date for update project

        var listBenefitsToUpdate = new ArrayList<BenefitWriteDto>();
        listBenefitsToUpdate.add(new BenefitWriteDto().setName("Apple Friday"));
        listBenefitsToUpdate.add(new BenefitWriteDto().setName("Orange Monday"));

        // Update benefits in project

        var actual = updatePut(location, ProjectReadDto.class, listBenefitsToUpdate);

        // Excepted data

        var listBenefitsToExcepted = new ArrayList<BenefitReadDto>();
        listBenefitsToExcepted.add(new BenefitReadDto()
                .setId(actual.getBenefits().get(0).getId())
                .setName("Apple Friday"));
        listBenefitsToExcepted.add(new BenefitReadDto()
                .setId(actual.getBenefits().get(1).getId())
                .setName("Orange Monday"));

        var excepted = new ProjectReadDto()
                .setId(actual.getId())
                .setName(actual.getName())
                .setDescription(actual.getDescription())
                .setSkills(actual.getSkills())
                .setBenefits(listBenefitsToExcepted)
                .setCompany(actual.getCompany());

        Assertions.assertThat(actual).isEqualTo(excepted);
    }

    @Test
    void shouldThatCreateAndUpdateSkillsNewProjectCorrect() {

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

        var location = projectUri + "/" + project.getId() + "/skills";

        // Date for update project

        var listSkillsToUpdate = new ArrayList<SkillWriteDto>();
        listSkillsToUpdate.add(new SkillWriteDto().setName("SQL"));
        listSkillsToUpdate.add(new SkillWriteDto().setName("Python"));

        // Update skills in project

        var actual = updatePut(location, ProjectReadDto.class, listSkillsToUpdate);

        // Excepted data

        var listSkillsToExcepted = new ArrayList<SkillReadDto>();
        listSkillsToExcepted.add(new SkillReadDto()
                .setId(actual.getSkills().get(0).getId())
                .setName("SQL"));
        listSkillsToExcepted.add(new SkillReadDto()
                .setId(actual.getSkills().get(1).getId())
                .setName("Python"));

        var excepted = new ProjectReadDto()
                .setId(actual.getId())
                .setName(actual.getName())
                .setDescription(actual.getDescription())
                .setSkills(listSkillsToExcepted)
                .setBenefits(actual.getBenefits())
                .setCompany(actual.getCompany());

        Assertions.assertThat(actual).isEqualTo(excepted);
    }
}
