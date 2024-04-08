package voluntarize.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tags_sq")
    @SequenceGenerator(name = "tags_sq", sequenceName = "tags_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ong_id")
    private Ong ong;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
