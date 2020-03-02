package murex.dev.mxem.Authorization.redis;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
@ToString
@Getter
@Builder
@AllArgsConstructor
@Data
public class Token implements Serializable{

    @Id String username;
    String token;

}