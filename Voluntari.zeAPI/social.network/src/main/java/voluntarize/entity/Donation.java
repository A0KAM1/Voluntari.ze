package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donations_sq")
    @SequenceGenerator(name = "donations_sq", sequenceName = "donations_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private double amount;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;
}
