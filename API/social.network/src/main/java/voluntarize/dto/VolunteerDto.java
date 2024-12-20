package voluntarize.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerDto {
    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String username;
    private String description;
    private String phoneNumber;
    private String profilePicture;
    private String city;
    private String state;
    private String country;
    private String cpf;
    private String lastName;
    private LocalDate birthday;
    private int level;
}
