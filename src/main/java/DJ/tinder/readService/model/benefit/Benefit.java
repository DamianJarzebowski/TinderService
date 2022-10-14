package DJ.tinder.readService.model.benefit;

import DJ.tinder.readService.model.BaseEntity;
import DJ.tinder.readService.model.project.Project;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Accessors(chain = true)
@Table(name = "benefits")
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Benefit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "benefits")
    private List<Project> projects;

}
