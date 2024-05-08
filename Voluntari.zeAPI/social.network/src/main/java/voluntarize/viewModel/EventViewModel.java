package voluntarize.viewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventViewModel {
    private Long id;
    private LocalDate date;
    private Time time;
    private String address;
    private String requirements;
    private Long statusId;
}
