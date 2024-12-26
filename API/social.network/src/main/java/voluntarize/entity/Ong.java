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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "government_code")
    private String governmentCode;
    private String address;
    @Column(name = "qr_code")
    private String qrCode;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
