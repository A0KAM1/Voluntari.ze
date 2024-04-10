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
@Table(name="volunteers")
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "volunteers_sq")
    @SequenceGenerator(name = "volunteers_sq", sequenceName = "volunteers_sq", initialValue = 1, allocationSize = 1)
    private Long id;
    private String email;
    private String password;
    @Column(unique = true)
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
    private String cpf;
    @Column(name = "last_name")
    private String lastName;
    private Date birthday;
    private int level;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
