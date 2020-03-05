package murex.dev.mxem.Authorization.config;

import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import murex.dev.mxem.Authorization.repository.TokenRepository;
import murex.dev.mxem.Authorization.service.TokenService;
import murex.dev.mxem.Users.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    TokenService tokenService;



    public JwtTokenVerifier(SecretKey secretKey,
                            JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(tokenService==null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            tokenService = webApplicationContext.getBean(TokenService.class);
        }

        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }
        log.info("Est ce que mon token repo est null :"+tokenService);
        log.info("Est ce que mon token repo est null :"+tokenService.tokenExists("gsdgsg").toString());


        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
        if(!tokenService.tokenExists(token)){
            filterChain.doFilter(request, response);
            return;
        }
        try {



            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
System.out.println("body");
            System.out.println(body);
            String username = body.getSubject();

            List<String> authorities = (List<String>) body.get("roles");
            System.out.println("Lololo");
            System.out.println(authorities);

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities= new HashSet<SimpleGrantedAuthority>();

            for(String role : authorities){
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role));
            }


            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(request, response);
    }

}
