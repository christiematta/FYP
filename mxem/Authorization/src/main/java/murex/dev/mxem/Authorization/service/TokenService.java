package murex.dev.mxem.Authorization.service;

import murex.dev.mxem.Authorization.redis.Token;
import murex.dev.mxem.Authorization.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements  ITokenService{

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void save(Token token){
        tokenRepository.save(token);
    }

    @Override
    public Token findById(String id){
        return tokenRepository.findById(id);
    }

    @Override
    public void update(Token token){
        tokenRepository.update(token);
    }

    @Override
    public void delete(String id){
        tokenRepository.delete(id);
    }

    @Override
    public Boolean tokenExists(String token){
        return tokenRepository.tokenExists(token);
    }

    @Override
    public String getUserFromToken(String token){
        return tokenRepository.getUserFromToken(token);
    }
}
