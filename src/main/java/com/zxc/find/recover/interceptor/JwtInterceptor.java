package com.zxc.find.recover.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxc.find.recover.utils.JwtUtils;
import com.zxc.find.recover.utils.Response;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Miya
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        Response defeat = Response.DEFEAT();
        if (token != null && !"".equals(token)) {
            try {
                JwtUtils.verity(token);
                return true;
            } catch (SignatureVerificationException e) {
                e.printStackTrace();
                defeat.carry("msg", "无效签名");
            } catch (TokenExpiredException e) {
                e.printStackTrace();
                defeat.carry("msg", "token过期");
            } catch (AlgorithmMismatchException e) {
                e.printStackTrace();
                defeat.carry("msg", "校验算法不一致");
            } catch (Exception e) {
                e.printStackTrace();
                defeat.carry("msg", "token无效！");
            }
        }
        defeat.carry("token", "请检查token");
        String json = new ObjectMapper().writeValueAsString(defeat);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
        return false;
    }
}
