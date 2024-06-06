package voluntarize.viewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowersViewModel {
    private Long user;
    private Long volunteer;
    private String name;
    private String lastName;
}
