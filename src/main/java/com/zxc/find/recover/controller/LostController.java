package com.zxc.find.recover.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxc.find.recover.entity.Lost;
import com.zxc.find.recover.service.impl.LostServiceImpl;
import com.zxc.find.recover.utils.JwtUtils;
import com.zxc.find.recover.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author YeYuShengFan
 * @Date 2021/11/26 15:38
 */
@RestController
@RequestMapping("/lost")
public class LostController {
    @Autowired
    private LostServiceImpl lostService;

    @GetMapping("/get")//首页获取失物信息
    public Response getLostIndex(){
        List<Lost> lostIndex = lostService.getLostIndex();
        if (!lostIndex.isEmpty()){
            return Response.SUCCEED().carry("lostIndex",lostIndex);
        }
        return Response.DEFEAT();
    }

    @GetMapping("/get/{id}")
    public Response getLostById(@PathVariable Integer id){
        Lost lost = lostService.getLostById(id);
        if (lost != null){
            return Response.SUCCEED().carry("lost",lost);
        }
        return Response.DEFEAT();
    }

    @PostMapping("/addLost")
    public void addLost(@RequestBody Lost lost, MultipartFile picture, HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        int userId = Integer.parseInt(verity.getClaim("userId").asString());
    }
}
