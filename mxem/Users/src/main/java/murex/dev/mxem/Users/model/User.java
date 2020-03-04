package murex.dev.mxem.Users.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="users")
public class User extends Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="name is mandatory. Please provide username")
    @Size(min=2, message="Name should be at least 2 characters")
    private String name;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name="user_role",
            joinColumns= @JoinColumn(name="userId", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="roleId", referencedColumnName="id"))
    Set<Role> roles;
}
