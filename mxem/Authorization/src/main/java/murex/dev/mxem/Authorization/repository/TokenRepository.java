package murex.dev.mxem.Authorization.repository;

import murex.dev.mxem.Authorization.redis.Token;


public interface TokenRepository{

    void save(Token token);

    Token findById(String id);

    void update(Token token);

    void delete(String id);

    Boolean tokenExists(String token);
}
