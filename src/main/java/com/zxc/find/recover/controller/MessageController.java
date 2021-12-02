package com.zxc.find.recover.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.entity.Lost;
import com.zxc.find.recover.entity.Message;
import com.zxc.find.recover.service.impl.MessageServiceImpl;
import com.zxc.find.recover.utils.JwtUtils;
import com.zxc.find.recover.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author YeYuShengFan
 * @Date 2021/12/1 20:15
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageServiceImpl messageService;

    @PostMapping("/addMessage")
    public Response addMessage(@RequestBody Find find, @RequestBody Lost lost, @RequestBody Message message, HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        int userId = Integer.parseInt(verity.getClaim("userId").asString());
        message.setUserId(userId);
        //判断这个评论是属于哪个表的
        if (find != null){
            message.setFindId(find.getId());
        }else if (lost != null){
            message.setLostId(lost.getId());
        }
        int add = messageService.addMessage(message);
        if (add > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PutMapping("/updateMessage")
    public Response updateMessage(@RequestBody Message message){
        int update = messageService.updateMessage(message);
        if (update > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @GetMapping("/deleteMessage/{id}")
    public Response deleteMessage(@PathVariable Integer id){
        int delete = messageService.deleteMessage(id);
        if (delete > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }
}
