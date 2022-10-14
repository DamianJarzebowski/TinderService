package DJ.tinder.readService.model.skill;

import DJ.tinder.readService.model.developer.Developer;
import DJ.tinder.readService.model.BaseEntity;
import DJ.tinder.readService.model.project.Project;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "skills")
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Skill extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "skills")
    List<Developer> developers;

    @ManyToMany(mappedBy = "skills")
    List<Project> projects;
}
