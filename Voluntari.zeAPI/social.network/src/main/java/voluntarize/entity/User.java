package voluntarize.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sq")
    @SequenceGenerator(name = "users_sq", sequenceName = "users_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String email;
    private String password;
    @Column(unique = true, length = 15)
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
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
