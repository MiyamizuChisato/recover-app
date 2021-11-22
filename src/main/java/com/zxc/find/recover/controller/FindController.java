package com.zxc.find.recover.controller;

import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.service.impl.FindServiceImpl;
import com.zxc.find.recover.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Miyam
 */
@RestController
@RequestMapping("/find")
public class FindController {
    @Autowired
    private FindServiceImpl service;

    @GetMapping("/get")
    public Response getFindIndex() {
        List<Find> finds = service.getFindIndex();
        if (!finds.isEmpty()) {
            return Response.SUCCEED().carry("finds", finds);
        }
        return Response.DEFEAT();
    }

    @GetMapping("/get/{id}")
    public Response getFindById(@PathVariable Integer id) {
        Find find = service.getFindById(id);
        if (find != null) {
            return Response.SUCCEED().carry("find", find);
        }
        return Response.DEFEAT();
    }

    @PostMapping("/post")
    public Response addFind(String name) {
        String s = name + "添加了一条记录";
        return Response.SUCCEED().carry("message", s);
    }
}