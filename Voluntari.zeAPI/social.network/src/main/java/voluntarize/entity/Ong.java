package voluntarize.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ongs")
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ongs_sq")
    @SequenceGenerator(name = "ongs_sq", sequenceName = "ongs_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String cnpj;
    private String address;
    @Column(name = "qr_code")
    private String qrCode;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
