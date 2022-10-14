package DJ.tinder.readService.model.company;

import DJ.tinder.readService.model.BaseEntity;
import DJ.tinder.readService.model.project.Project;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "companies")
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Project> projects;

}
