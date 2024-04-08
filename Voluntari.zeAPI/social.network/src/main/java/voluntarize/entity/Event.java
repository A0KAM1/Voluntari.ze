package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_sq")
    @SequenceGenerator(name = "events_sq", sequenceName = "events_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private Date date;
    private Date time;
    private String address;
    private String requirements;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
