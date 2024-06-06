package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "presence")
public class Presence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "presence_sq")
    @SequenceGenerator(name = "presence_sq", sequenceName = "presence_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String name;
}
