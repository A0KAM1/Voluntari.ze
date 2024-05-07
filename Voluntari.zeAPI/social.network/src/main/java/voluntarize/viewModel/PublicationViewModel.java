package voluntarize.viewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationViewModel {
    private Long id;
    private String description;
    private List<String> photos;
    private Long ongId;
    private Long postId;
    private int likes;
}
