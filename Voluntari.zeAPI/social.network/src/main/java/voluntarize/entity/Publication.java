package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="publications")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publications_sq")
    @SequenceGenerator(name = "publications_sq", sequenceName = "publications_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
