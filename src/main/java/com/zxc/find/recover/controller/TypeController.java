package com.zxc.find.recover.controller;

import com.zxc.find.recover.entity.Type;
import com.zxc.find.recover.service.TypeService;
import com.zxc.find.recover.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Miyam
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService service;

    @GetMapping("/get")
    public Response getAllType() {
        List<Type> types = service.getAllType();
        if (!types.isEmpty()) {
            return Response.SUCCEED().carry("types", types);
        }
        return Response.DEFEAT();
    }
}
