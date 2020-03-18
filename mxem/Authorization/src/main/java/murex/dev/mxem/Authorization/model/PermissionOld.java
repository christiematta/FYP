package murex.dev.mxem.Authorization.model;

import lombok.*;


import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PermissionOld extends Structure {
    private Long id;
    private String name;
    Set<RoleOld> roleOlds;
}
