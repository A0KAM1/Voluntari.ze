package voluntarize.viewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowingViewModel {
    private Long id;
    private Long userId;
    private String name;
    private String profilePic;
}
