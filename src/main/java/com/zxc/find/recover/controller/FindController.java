package com.zxc.find.recover.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.service.impl.FindServiceImpl;
import com.zxc.find.recover.utils.JwtUtils;
import com.zxc.find.recover.utils.Response;
import com.zxc.find.recover.utils.TypeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miyam
 */
@RestController
@RequestMapping("/find")
public class FindController {
    @Autowired
    private FindServiceImpl service;

    @GetMapping("/get")  //首页
    public Response getFindIndex() {
        List<Find> finds = service.getFindIndex();
        List<Find> eleFinds = new ArrayList<>();
        List<Find> cloFinds = new ArrayList<>();
        for (Find find : finds) {
            if (find.getArticle().getType().getId() == 1) {
                find.setStartUser(null);
                eleFinds.add(find);
            }
            if (find.getArticle().getType().getId() == 2) {
                find.setStartUser(null);
                cloFinds.add(find);
            }
        }
        TypeItem eleItem = new TypeItem();
        TypeItem cloItem = new TypeItem();
        eleItem.setName("电子产品");
        eleItem.setItemList(eleFinds);
        cloItem.setName("服装类");
        cloItem.setItemList(cloFinds);
        List<TypeItem> typeList = new ArrayList<>();
        typeList.add(eleItem);
        typeList.add(cloItem);
        if (!finds.isEmpty()) {
            return Response.SUCCEED().carry("typeList", typeList);
        }
        return Response.DEFEAT();
    }

    @GetMapping("/get/{id}") // 根据id获取
    public Response getFindById(@PathVariable Integer id) {
        Find find = service.getFindById(id);
        Find editFind = service.getFindById(id);
        service.viewCountPlus(editFind);
        if (find != null) {
            return Response.SUCCEED().carry("find", find);
        }
        return Response.DEFEAT();
    }

    @PostMapping("/post") // 添加新的Find
    public Response addFind(@RequestBody Find find, MultipartFile picture, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        Integer userId = Integer.parseInt(verity.getClaim("userId").asString());
        find.setStartUserId(userId);
        int i = service.addNewFind(find, picture);
        if (i > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PutMapping("/put") // 修改Find
    public Response updateFind(@RequestBody Find find) {
        int update = service.updateFind(find);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/post/recognize") // 开始认领
    public Response startFind(@RequestBody Find find, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        Integer userId = Integer.parseInt(verity.getClaim("userId").asString());
        find.setEndUserId(userId);
        int update = service.updateFind(find);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/post/finish") // 完成认领
    public Response endFind(@RequestBody Find find) {
        Find finish = service.getFindById(find.getId());
        finish.setClaim(1);
        int update = service.updateFind(finish);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/post/cancel") // 取消认领
    private Response cancelFind(@RequestBody Find find) {
        Find cancel = service.getFindById(find.getId());
        cancel.setEndUserId(0);
        int update = service.updateFind(cancel);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }
}