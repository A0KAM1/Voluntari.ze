package voluntarize.request;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class EventRequest {
    public Long ongId;
    public String description;
    public List<String> photos;
    public String requirements;
    public Date date;
    public Time time;
    public String address;
}
