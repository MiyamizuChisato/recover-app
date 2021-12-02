package com.zxc.find.recover.controller;

import com.zxc.find.recover.entity.Type;
import com.zxc.find.recover.service.TypeService;
import com.zxc.find.recover.service.impl.TypeServiceImpl;
import com.zxc.find.recover.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Miyam
 */
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeServiceImpl typeService;

    @GetMapping("/get")
    public Response getAllType() {
        List<Type> types = typeService.getAllType();
        if (!types.isEmpty()) {
            return Response.SUCCEED().carry("types", types);
        }
        return Response.DEFEAT();
    }

    @PostMapping("/addType")
    public Response addType(@RequestBody Type type){
        int add = typeService.addType(type);
        if(add > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/updateType")
    public Response updateType(@RequestBody Type type){
        int update = typeService.updateType(type);
        if (update > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @GetMapping("/deleteType/{id}")
    public Response deleteType(@PathVariable Integer id){
        int delete = typeService.deleteType(id);
        if (delete > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }
}
