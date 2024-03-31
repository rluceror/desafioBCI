package cl.bci.desafio.utilities.jwt;

import cl.bci.desafio.data.BciRepository;
import cl.bci.desafio.data.dtos.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static cl.bci.desafio.utilities.AppConstant.EXPIRED_TIME_TOKEN_SESSION;
import static cl.bci.desafio.utilities.AppConstant.NAME_TOKEN_SESSION;

@Component
public class JwtTokenProvider {

  @Value("${security.jwt.token.secret-key:secret}")
  private String secretKey = "secret";

  @Value("${security.jwt.token.expire-length:300000}")
  private long validityInMilliseconds = EXPIRED_TIME_TOKEN_SESSION;

  @Autowired private BciRepository bciRepository;

  @Autowired private UserDetailsService userDetailsService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String username, List<String> roles) {

    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", roles);

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }

  public Authentication getAuthentication(String token) {
    Authentication authentication = null;

    final UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));

    if (userDetails != null) {
      authentication =
          new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    return authentication;
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    String token = null;
    final String bearerToken = req.getHeader(NAME_TOKEN_SESSION);
    if (bearerToken != null) {
      token = bearerToken;
    }
    return token;
  }

  public boolean validateToken(String token) {
    boolean validateToken = true;
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

      final User findTokenDB = bciRepository.findUserByToken(token);

      try {
        if (findTokenDB.getToken().equals(token)
            && claims.getBody().getExpiration().before(new Date())) {
          validateToken = false;
        }
      } catch (NullPointerException ex) {
        validateToken = false;
      }

    } catch (JwtException | IllegalArgumentException | ClassCastException e) {
      validateToken = false;
    }
    return validateToken;
  }

  public String revocateToken(String username, List<String> roles) {

    Claims claims = Jwts.claims().setSubject(username);
    claims.put("roles", roles);

    Date now = new Date();
    Date validity = new Date(now.getTime() - validityInMilliseconds);

    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(validity)
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();
  }
}
