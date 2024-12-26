package voluntarize.viewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OngViewModel {
    public Long userId;
    public Long ongId;
    public String name;
    public String username;
    public String email;
    public String description;
    public String profilePicture;
    public String phoneNumber;
    public String governmentCode;
    public String qrCode;
    public String address;
    public String city;
    public String state;
    public String country;
}
