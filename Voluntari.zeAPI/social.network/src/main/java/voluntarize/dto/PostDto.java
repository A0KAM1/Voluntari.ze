package voluntarize.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String content;
    private Long ongId;
    private Date createdAt;
    private Date updatedAt;
    private Long publication;
    private EventDto event;
    private List<String> pictures;
    private int likes;
}
