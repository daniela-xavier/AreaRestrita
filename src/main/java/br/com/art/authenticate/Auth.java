/**
 * Auth.java
 * Created on 10 de out de 2019
 * 
 *
 */

package br.com.art.authenticate;

import javax.servlet.http.HttpServletRequest;

import br.com.art.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * 
 * Description the class  Auth.java 
 *
 * @Autor daniela.conceicao 
 * @since
 * @version  %I%, %G% 
 */
public class Auth {

    /**
     * Metodo que verifica a autenticidade do token enviado e retorna a entidade
     * que estao solicitando.
     *
     * @param request - HttpServletRequest solicitada.
     * @return DomainEntity
     */
    public static Token getUserToken(HttpServletRequest request) {
        String token = request.getHeader(JWTUtilToken.TOKEN_HEADER_AUTHENTICATION);
        
        if (token != null) {

            Jws<Claims> jws = JWTUtilToken.decodificarToken(token);
            if (jws != null) {
                String user = jws.getBody().getSubject();
                System.out.println("User request: " + user);
                Token me = new Token(user, token);              
                return me;
            }
        }
        return null;
    }

    /**
     * Metodo que verifica a autenticidade do token enviado e retorna a entidade
     * que estao solicitando.
     *
     * @param request - HttpServletRequest solicitada.
     * @return DomainEntity
     */
    public static Boolean getValidUser(HttpServletRequest request) {
        String token = request.getHeader(JWTUtilToken.TOKEN_HEADER_AUTHENTICATION);

        if (token != null) {
            Jws<Claims> jws;
            try {
                jws = JWTUtilToken.decodificarToken(token);
            } catch (Exception e) {
                return true;
            }

            if (jws != null) {
                String user = jws.getBody().getSubject();
                if (user != null || !user.isEmpty()) {                    
                   
                    return true;
                }

            }
            return true;
        }
        return true;
    }
}
