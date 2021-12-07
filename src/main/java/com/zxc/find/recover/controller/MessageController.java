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
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author YeYuShengFan
 * @Date 2021/12/1 20:15
 */
@RestController
@RequestMapping("/message")
public class MessageController extends CRUDController<Message>{
    @Autowired
    private MessageServiceImpl messageService;

    //添加新评论，由于CRUDController定义的参数无法判断评论信息所属于哪种启示，则自己定义一个方法
    @PostMapping("/add")
    public Response addMessage(@RequestBody Find find, @RequestBody Lost lost, @RequestBody Message message, HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        int userId = Integer.parseInt(verity.getClaim("userId").asString());
        Timestamp timestamp = new Timestamp(new Date().getTime());
        message.setDate(timestamp);
        message.setUserId(userId);
        //判断这个评论是属于哪个表的
        if (find != null){
            message.setFindId(find.getId());
        }else if (lost != null){
            message.setLostId(lost.getId());
        }
        int add = messageService.addNewInfo(message);
        if (add > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PutMapping("/update")
    @Override
    public Response update(@RequestBody Message message) {
        return super.update(message);
    }

    @GetMapping("/delete/{id}")
    @Override
    public Response delete(@PathVariable Integer id) {
        return super.delete(id);
    }
}
