package com.zxc.find.recover.controller;

import com.zxc.find.recover.entity.Type;
import com.zxc.find.recover.service.impl.TypeServiceImpl;
import com.zxc.find.recover.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Miyam
 */
@RestController
@RequestMapping("/type")
public class TypeController extends CRUDController<Type>{
    @Autowired
    private TypeServiceImpl typeService;

    @GetMapping("/get")
    @Override
    public Response getIndex() {
        List<Type> types = typeService.getIndex();
        if (!types.isEmpty()) {
            return Response.SUCCEED().carry("types", types);
        }
        return Response.DEFEAT();
    }

    @PostMapping("/add")
    @Override
    public Response add(@RequestBody Type type, HttpServletRequest request) {
        int add = typeService.addNewInfo(type);
        if (add > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/update")
    @Override
    public Response update(@RequestBody Type type) {
        return super.update(type);
    }

    @GetMapping("/delete/{id}")
    @Override
    public Response delete(@PathVariable Integer id) {
        return super.delete(id);
    }
}
