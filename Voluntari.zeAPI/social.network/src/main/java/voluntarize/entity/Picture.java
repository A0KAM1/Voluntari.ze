package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pictures_sq")
    @SequenceGenerator(name = "pictures_sq", sequenceName = "pictures_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String url;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
