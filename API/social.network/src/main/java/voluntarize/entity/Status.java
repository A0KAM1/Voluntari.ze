package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_sq")
    @SequenceGenerator(name = "status_sq", sequenceName = "status_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    @Column(name = "status_name")
    private String statusName;
}
