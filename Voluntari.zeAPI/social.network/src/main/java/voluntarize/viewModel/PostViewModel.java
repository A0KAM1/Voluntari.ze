package voluntarize.viewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostViewModel {
    private Long id;
    private Long ongId;
    private PublicationViewModel publication;
    private EventViewModel event;
    private Date createdAt;
    private Date updatedAt;
    private int likes;
}
