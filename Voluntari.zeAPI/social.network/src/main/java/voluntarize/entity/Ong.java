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
@Table(name = "ongs")
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ongs_sq")
    @SequenceGenerator(name = "ongs_sq", sequenceName = "ongs_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String email;
    private String password;
    @Column(unique = true, length = 5)
    private String username;
    private String description;
    private String name;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "profile_picture")
    private String profilePicture;
    private String city;
    private String state;
    private String country;
    private String cnpj;
    private String address;
    @Column(name = "qr_code")
    private String qrCode;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
