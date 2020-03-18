package murex.dev.mxem.Authorization.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserOld extends Structure {
    private Long id;
    private String name;
    RoleOld[] roleOlds;
}
