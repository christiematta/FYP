package murex.dev.mxem.Environments.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection="projects")
public class Project {
    @Id
    public String id;
    public String name;
    public Boolean isDeleted;
    public String createdOn;
    public String createdBy;
    public String modifiedOn;
    public String modifiedBy;
    public String owner;
}
