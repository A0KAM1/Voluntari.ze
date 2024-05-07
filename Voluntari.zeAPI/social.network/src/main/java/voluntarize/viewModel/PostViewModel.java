package voluntarize.viewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import voluntarize.entity.Event;
import voluntarize.entity.Picture;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostViewModel {
    private Long id;
    private String content;
    private Long ongId;
    private Date createdAt;
    private Time updatedAt;
    private Long publication;
    private Event event;
    private List<String> pictures;
    private int likes;
}
