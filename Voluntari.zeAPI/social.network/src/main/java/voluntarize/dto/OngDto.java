package voluntarize.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OngDto {
    private Long userId;
    private Long ongId;
    private String governmentCode;
    private String address;
    private String qrCode;
    private String name;
    private String username;
    private String email;
    private String description;
    private String phoneNumber;
    private String profilePicture;
    private String city;
    private String state;
    private String country;
}
