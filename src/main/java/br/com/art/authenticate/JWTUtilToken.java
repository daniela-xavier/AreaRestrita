/**
 * JWTUtilToken.java
 * Created on 10 de out de 2019
 * 
 *
 */

package br.com.art.authenticate;

import java.util.Calendar;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * Description the class  JWTUtilToken.java 
 *
 * @Autor daniela.conceicao 
 * @since
 * @version  %I%, %G% 
 */
public abstract class JWTUtilToken {

    //Váriavel de configuração do token.    

    /**
     *
     */
    public static final String KEY = "CONTINUE_A_NADAR";

    //Váriavel de auditoria do token.

    /**
     *
     */
    public static final String AUD = "PARA_ACHAR_A_SOLUCAO_NADAR";

    //Váriavel de identificação do token no header da requisição.    

    /**
     *
     */
    public static final String TOKEN_HEADER_AUTHENTICATION = "Authentication";

    //Váriavel identificadora da plataforma.

    /**
     *
     */
    public static final String TOKEN_HEADER_API = "APIAuthentication";

    /**
     * Método que cria a String do token.
     *
     * @param subject - sujeito do token.
     * @return String
     * @throws IllegalAccessException 
     */
    public static String criarToken(String subject) throws IllegalAccessException {
        String token = "";
        try {
            token = Jwts.builder()
                    .setSubject(subject)
                    .setAudience(AUD)
                    .signWith(SignatureAlgorithm.HS512, KEY)
                    .compact();
        } catch (Exception e) {
            throw new IllegalAccessException("Não foi possivel realizar a criação do token. Exception" + e.getMessage());
        }
        return token;
    }

    /**
     * Método que decodifica o token e retorna um JWS com os atributos
     * para validação.
     *
     * @param token - Token enviado.
     * @return JWS
     */
    public static Jws<Claims> decodificarToken(String token) {
        if (validarToken(token)) {
            return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
        }
        return null;
    }

    /**
     * Método que renova o token de acordo com as entradas passada.
     *
     * @param token - Token enviado.
     * @param subject - Sujeito do token.
     * @return String.
     * @throws IllegalAccessException 
     */
    public static String renovarToken(String token, String subject) throws IllegalAccessException {
        Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
        Date expiration = parseClaimsJws.getBody().getExpiration();

        if (expiration.after(Calendar.getInstance().getTime())) {
            return criarToken(subject);
        }
        return null;
    }

    /**
     * Método que faz a validação do token de acordo com o a palavra Passe.
     *
     * @param token - Token enviado.
     * @return boolean
     */
    public static boolean validarToken(String token) {
        Jws<Claims> jws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token);
        return jws.getBody().getAudience().equals(AUD);
    }
}

