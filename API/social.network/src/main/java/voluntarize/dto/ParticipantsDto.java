package voluntarize.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import voluntarize.entity.Presence;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantsDto {
    private String name;
    private String lastName;
    private String presence;
}
