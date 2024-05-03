package voluntarize.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import voluntarize.entity.Picture;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicationDto {
    private Long id;
    private String description;
    private List<Picture> photos;
    private Long ongId;
}
