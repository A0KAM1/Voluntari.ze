package voluntarize.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EventRequest {
    public LocalDate date;
    public String time;
    public String address;
    public String requirements;
    public String description;
    public Long ongId;
    public List<String> photos;
}
