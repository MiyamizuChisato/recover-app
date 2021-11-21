package com.zxc.find.recover.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxc.find.recover.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Miya
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Map<String, Object> map = new HashMap<>(12);
        if (!"".equals(token)) {
            try {
                JwtUtils.verity(token);
                return true;
            } catch (SignatureVerificationException e) {
                e.printStackTrace();
                map.put("msg", "无效签名");
            } catch (TokenExpiredException e) {
                e.printStackTrace();
                map.put("msg", "token过期");
            } catch (AlgorithmMismatchException e) {
                e.printStackTrace();
                map.put("msg", "校验算法不一致");
            } catch (Exception e) {
                e.printStackTrace();
                map.put("msg", "token无效！");
            }
        }
        map.put("state", false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
        return false;
    }
}
