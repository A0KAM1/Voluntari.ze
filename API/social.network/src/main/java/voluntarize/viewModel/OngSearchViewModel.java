package voluntarize.viewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OngSearchViewModel {
    public Long id;
    public Long userId;
    public String name;
    public String username;
    public String profilePicture;
}
