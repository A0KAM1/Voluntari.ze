package voluntarize.viewModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventViewModel {
    private Long id;
    private LocalDate date;
    private Time time;
    private String address;
    private String requirements;
    private String description;
    private List<String> photos;
    private Long statusId;
    private Long ongId;
    private Long postId;
    private int likes;
}
