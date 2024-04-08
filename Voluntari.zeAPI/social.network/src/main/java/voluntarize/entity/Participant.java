package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participants_sq")
    @SequenceGenerator(name = "participants_sq", sequenceName = "participants_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
