package DJ.tinder.developerTest;

import DJ.tinder.readService.model.achievement.dto.AchievementReadDto;
import DJ.tinder.readService.model.developer.dto.DeveloperReadDto;
import DJ.tinder.readService.model.skill.dto.SkillReadDto;
import DJ.tinder.writeService.model.achievement.dto.AchievementWriteDto;
import DJ.tinder.writeService.model.developer.dto.DeveloperWriteDto;
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
public class DeveloperControllerTest {

    public static final String BASE_URL = "/developers";
    String baseUri;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void beforeEach() {
        baseUri = URI.create(testRestTemplate.getRootUri()) + BASE_URL;
    }

    @Test
    void shouldThatCreateAndGetNewDeveloperCorrect() {

        var listDeveloperSkills = new ArrayList<SkillWriteDto>();
        listDeveloperSkills.add(new SkillWriteDto().setName("Java"));
        listDeveloperSkills.add(new SkillWriteDto().setName("GIT"));

        var listDeveloperAchievements = new ArrayList<AchievementWriteDto>();
        listDeveloperAchievements.add(new AchievementWriteDto().setName("Java"));
        listDeveloperAchievements.add(new AchievementWriteDto().setName("GIT"));

        var dataToCreateDeveloper = new DeveloperWriteDto()
                .setFirstName("TestDevFirstName")
                .setLastName("TestDevLastName")
                .setDescription("Sth")
                .setProfession("TestProfession")
                .setAchievements(listDeveloperAchievements)
                .setSkills(listDeveloperSkills);

        var developer = create(baseUri, DeveloperReadDto.class, dataToCreateDeveloper);

        var locationDeveloper = baseUri + "/" + developer.getId();

        var actual = read(locationDeveloper, DeveloperReadDto.class);

        var listDeveloperSkillsExcepted = new ArrayList<SkillReadDto>();
        listDeveloperSkillsExcepted.add(new SkillReadDto()
                .setId(actual.getSkills().get(0).getId())
                .setName("Java"));
        listDeveloperSkillsExcepted.add(new SkillReadDto()
                .setId(actual.getSkills().get(1).getId())
                .setName("GIT"));

        var listDeveloperAchievementsExcepted = new ArrayList<AchievementReadDto>();
        listDeveloperAchievementsExcepted.add(new AchievementReadDto()
                .setId(actual.getAchievements().get(0).getId())
                .setName("Java"));
        listDeveloperAchievementsExcepted.add(new AchievementReadDto()
                .setId(actual.getAchievements().get(1).getId())
                .setName("GIT"));

        var excepted = new DeveloperReadDto()
                .setId(actual.getId())
                .setFirstName("TestDevFirstName")
                .setLastName("TestDevLastName")
                .setDescription("Sth")
                .setProfession("TestProfession")
                .setAchievements(listDeveloperAchievementsExcepted)
                .setSkills(listDeveloperSkillsExcepted);

        Assertions.assertThat(actual).isEqualTo(excepted);
    }

    @Test
    void shouldThatCreateAndUpdateNewDeveloperCorrectBasicInformation() {

        var listDeveloperSkills = new ArrayList<SkillWriteDto>();
        listDeveloperSkills.add(new SkillWriteDto().setName("Java"));
        listDeveloperSkills.add(new SkillWriteDto().setName("GIT"));

        var listDeveloperAchievements = new ArrayList<AchievementWriteDto>();
        listDeveloperAchievements.add(new AchievementWriteDto().setName("Achievement1"));
        listDeveloperAchievements.add(new AchievementWriteDto().setName("Achievement2"));

        var dataToCreateDeveloper = new DeveloperWriteDto()
                .setFirstName("TestDevFirstName")
                .setLastName("TestDevLastName")
                .setDescription("Sth")
                .setProfession("TestProfession")
                .setSkills(listDeveloperSkills)
                .setAchievements(listDeveloperAchievements);

        var developer = create(baseUri, DeveloperReadDto.class, dataToCreateDeveloper);

        var locationDeveloper = baseUri + "/" + developer.getId() + "/general";

        var dataToUpdateDeveloper = new DeveloperWriteDto()
                .setFirstName("UpdateFirstName")
                .setLastName("UpdateLastName")
                .setDescription("UpdateSth")
                .setProfession("UpdateProfession")
                .setAchievements(listDeveloperAchievements)
                .setSkills(listDeveloperSkills);

        var actual = updatePut(locationDeveloper, DeveloperReadDto.class, dataToUpdateDeveloper);

        var excepted = new DeveloperReadDto()
                .setId(developer.getId())
                .setFirstName("UpdateFirstName")
                .setLastName("UpdateLastName")
                .setDescription("UpdateSth")
                .setProfession("UpdateProfession")
                .setAchievements(developer.getAchievements())
                .setSkills(developer.getSkills());

        Assertions.assertThat(actual).isEqualTo(excepted);
    }

    @Test
    void shouldThatCreateAndUpdateAchievementsNewDeveloperCorrect() {

        var listDeveloperSkills = new ArrayList<SkillWriteDto>();
        listDeveloperSkills.add(new SkillWriteDto().setName("Java"));
        listDeveloperSkills.add(new SkillWriteDto().setName("GIT"));

        var listDeveloperAchievements = new ArrayList<AchievementWriteDto>();
        listDeveloperAchievements.add(new AchievementWriteDto().setName("Achievement1"));
        listDeveloperAchievements.add(new AchievementWriteDto().setName("Achievement2"));

        var dataToCreateDeveloper = new DeveloperWriteDto()
                .setFirstName("TestDevFirstName")
                .setLastName("TestDevLastName")
                .setDescription("Sth")
                .setProfession("TestProfession")
                .setSkills(listDeveloperSkills)
                .setAchievements(listDeveloperAchievements);

        var developer = create(baseUri, DeveloperReadDto.class, dataToCreateDeveloper);

        var locationDeveloper = baseUri + "/" + developer.getId() + "/achievements";

        var listAchievementsToUpdate = new ArrayList<AchievementWriteDto>();
        listAchievementsToUpdate.add(new AchievementWriteDto().setName("Achievement1_Updated"));
        listAchievementsToUpdate.add(new AchievementWriteDto().setName("Achievement2_Updated"));


        var actual = updatePut(locationDeveloper, DeveloperReadDto.class, listAchievementsToUpdate);

        var expectedListDeveloperAchievement = new ArrayList<AchievementReadDto>();
        expectedListDeveloperAchievement.add(new AchievementReadDto()
                .setId(actual.getSkills().get(0).getId())
                .setName("Achievement1_Updated"));
        expectedListDeveloperAchievement.add(new AchievementReadDto()
                .setId(actual.getSkills().get(1).getId())
                .setName("Achievement2_Updated"));

        var excepted = new DeveloperReadDto()
                .setId(developer.getId())
                .setFirstName(developer.getFirstName())
                .setLastName(developer.getLastName())
                .setDescription(developer.getDescription())
                .setProfession(developer.getProfession())
                .setAchievements(expectedListDeveloperAchievement)
                .setSkills(developer.getSkills());

        Assertions.assertThat(actual).isEqualTo(excepted);

    }

    @Test
    void shouldThatCreateAndUpdateSkillsNewDeveloperCorrect() {

        var listDeveloperSkills = new ArrayList<SkillWriteDto>();
        listDeveloperSkills.add(new SkillWriteDto().setName("Java"));
        listDeveloperSkills.add(new SkillWriteDto().setName("GIT"));

        var listDeveloperAchievements = new ArrayList<AchievementWriteDto>();
        listDeveloperAchievements.add(new AchievementWriteDto().setName("Achievement1"));
        listDeveloperAchievements.add(new AchievementWriteDto().setName("Achievement2"));

        var dataToCreateDeveloper = new DeveloperWriteDto()
                .setFirstName("TestDevFirstName")
                .setLastName("TestDevLastName")
                .setDescription("Sth")
                .setProfession("TestProfession")
                .setSkills(listDeveloperSkills)
                .setAchievements(listDeveloperAchievements);

        var developer = create(baseUri, DeveloperReadDto.class, dataToCreateDeveloper);

        var locationDeveloper = baseUri + "/" + developer.getId() + "/skills";

        var listSkillsToUpdate = new ArrayList<SkillWriteDto>();
        listSkillsToUpdate.add(new SkillWriteDto().setName("Update Skill 1"));
        listSkillsToUpdate.add(new SkillWriteDto().setName("Update Skill 2"));

        var actual = updatePut(locationDeveloper, DeveloperReadDto.class, listSkillsToUpdate);

        var expectedListDeveloperSkills = new ArrayList<SkillReadDto>();
        expectedListDeveloperSkills.add(new SkillReadDto()
                .setId(actual.getSkills().get(0).getId())
                .setName("Update Skill 1"));
        expectedListDeveloperSkills.add(new SkillReadDto()
                .setId(actual.getSkills().get(1).getId())
                .setName("Update Skill 2"));

        var excepted = new DeveloperReadDto()
                .setId(developer.getId())
                .setFirstName(developer.getFirstName())
                .setLastName(developer.getLastName())
                .setDescription(developer.getDescription())
                .setProfession(developer.getProfession())
                .setAchievements(developer.getAchievements())
                .setSkills(expectedListDeveloperSkills);

        Assertions.assertThat(actual).isEqualTo(excepted);
    }
}