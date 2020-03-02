package murex.dev.mxem.Authorization.service;

import murex.dev.mxem.Authorization.redis.Token;

public interface ITokenService {
    void save(Token token);

    Token findById(String id);

    void update(Token token);

    void delete(String id);

    Boolean tokenExists(String token);

    String getUserFromToken(String token);

}
