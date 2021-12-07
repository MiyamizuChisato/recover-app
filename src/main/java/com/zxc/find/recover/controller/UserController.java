package com.zxc.find.recover.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxc.find.recover.entity.User;
import com.zxc.find.recover.service.impl.UserServiceImpl;
import com.zxc.find.recover.utils.JwtUtils;
import com.zxc.find.recover.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Miyam
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @PostMapping("/login")
    public Response login(@RequestBody User user) {
        if (StringUtils.hasLength(user.getEmail()) && StringUtils.hasLength(user.getPassword())) {
            User login = service.login(user);
            if (login != null) {
                Map<String, String> map = new HashMap<>();
                map.put("userId", login.getId().toString());
                map.put("userEmail", login.getEmail());
                map.put("userName", login.getName());
                map.put("userQQ", login.getQq());
                map.put("userWechat", login.getWechat());
                map.put("userPhone", login.getPhone());
                map.put("userAvatar", login.getAvatar());
                map.put("userGender", login.getGender().toString());
                String token = JwtUtils.getToken(map);
                return Response.SUCCEED().carry("token", token);
            }
            return Response.DEFEAT().carry("defeat", "信息有误");
        }
        return Response.DEFEAT().carry("defeat", "接口调用无效");
    }

    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        if (StringUtils.hasLength(user.getEmail())
                && StringUtils.hasLength(user.getPassword())
                && StringUtils.hasLength(user.getName())) {
            int i = service.register(user);
            if (i > 0) {
                return Response.SUCCEED().carry("success", "注册成功");
            }
            return Response.DEFEAT().carry("defeat", "填写信息有误");
        }
        return Response.DEFEAT().carry("defeat", "借口调用无效");
    }

    @PutMapping("/update")
    public Response update(@RequestBody User user){
        int success = service.updateUser(user);
        if (success > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @GetMapping("/get")
    public Response getUserById(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        Integer userId = Integer.parseInt(verity.getClaim("userId").asString());
        User user = service.getUserById(userId);
        if (user != null){
            return Response.SUCCEED().carry("user",user);
        }
        return Response.DEFEAT();
    }
}
