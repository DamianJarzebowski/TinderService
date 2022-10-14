package DJ.tinder.readService.model.project;

import DJ.tinder.readService.model.BaseEntity;
import DJ.tinder.readService.model.company.Company;
import DJ.tinder.readService.model.skill.Skill;

import DJ.tinder.readService.model.benefit.Benefit;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "projects")
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name="projects_skills",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name="projects_benefits",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "benefits_id")
    )
    private List<Benefit> benefits;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

}
