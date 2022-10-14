package DJ.tinder.readService.model.developer;

import DJ.tinder.readService.model.BaseEntity;
import DJ.tinder.readService.model.achievement.Achievement;
import DJ.tinder.readService.model.skill.Skill;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "developers")
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Developer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String description;

    private String profession;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "developers_achievements",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "achievement_id")
    )
    private List<Achievement> achievements;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "developers_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

}
