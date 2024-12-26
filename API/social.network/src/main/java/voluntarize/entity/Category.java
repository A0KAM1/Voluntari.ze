package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_sq")
    @SequenceGenerator(name = "categories_sq", sequenceName = "categories_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String name;
}
