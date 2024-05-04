package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="volunteers")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "volunteers_sq")
    @SequenceGenerator(name = "volunteers_sq", sequenceName = "volunteers_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String cpf;
    @Column(name = "last_name")
    private String lastName;
    private LocalDate birthday;
    private int level;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
