package voluntarize.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import voluntarize.entity.Event;
import voluntarize.entity.Picture;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String content;
    private Long ongId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Long publication;
    private Event event;
    private List<Picture> pictures;
    private int likes;
}
