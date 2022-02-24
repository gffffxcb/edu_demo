package com.mgh.commanUtils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * @author MGH
 * @create 2022-0223 9:58 下午
 */
@Slf4j
public class JwtUtil {

    /**
    * 设置token过期时间
    */
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    /**
     * 设置私钥
     */
    public static final String APP_SECRET = "qwertyuiop";

    /**
     * 根据id生成token
     */
    public static String getJwtToken(String id) {
        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("edu-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return JwtToken;
    }

    /**
     * 判断token是否存在与有效 * @param jwtToken
     *
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            throw new RuntimeException("token 无效，请重新获取");
        }
        return true;
    }

    /**
     * 判断token是否存在与有效 * @param request
     *
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if (StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            throw new RuntimeException("token 无效，请重新获取");
        }
        return true;
    }

    /**
     * 根据token获取会员id * @param request
     *
     * @return
     */
    public static String getIdByJwtToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }
}
