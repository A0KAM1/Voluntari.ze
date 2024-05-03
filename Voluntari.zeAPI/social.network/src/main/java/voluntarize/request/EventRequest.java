package voluntarize.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventRequest {
    public Long ongId;
    public String description;
    public List<String> photos;
    public String requirements;
    public LocalDate date;
    public String time;
    public String address;
}
