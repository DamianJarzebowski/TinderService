package DJ.tinder.readService.model.achievement;

import DJ.tinder.readService.model.BaseEntity;
import DJ.tinder.readService.model.developer.Developer;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "achievements")
@Builder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Achievement extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "achievements")
    private List<Developer> developers;

}
