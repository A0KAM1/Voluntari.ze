package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="followers")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "followers_sq")
    @SequenceGenerator(name = "followers_sq", sequenceName = "followers_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ong_id")
    private Ong ong;
    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;
}
