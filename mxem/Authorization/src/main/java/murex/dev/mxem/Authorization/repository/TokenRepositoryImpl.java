package murex.dev.mxem.Authorization.repository;

import murex.dev.mxem.Authorization.redis.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class TokenRepositoryImpl implements TokenRepository {
    private static final String KEY = "TOKEN";

    private RedisTemplate<String, Token> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public TokenRepositoryImpl(RedisTemplate<String, Token> redisTemplate){
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Token token) {
        hashOperations.put(KEY, token.getUsername(), token);
    }

    @Override
    public Token findById(String id) {
        return (Token)hashOperations.get(KEY, id);
    }

    @Override
    public void update(Token token) {
        save(token);
    }

    @Override
    public void delete(String id) {
        hashOperations.delete(KEY, id);
    }

    @Override
    public Boolean tokenExists(String token) {
        Map<String, Token> tokens= hashOperations.entries(KEY);

        for (Map.Entry<String, Token> entry : tokens.entrySet()) {
            Token value= entry.getValue();
            if(value.getToken().equals(token)){
                return true;
            }
        }
        return false;
    }
}
