package murex.dev.mxem.Environments.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection="requests")
public class Request {
    @Id
    public String id;
    public String name;
    public String type;
    public String envId;
    public Environment environment;
    public String operation;
    public String status;
    public List<Event> events;
    public Date date;
    public String user;
}
