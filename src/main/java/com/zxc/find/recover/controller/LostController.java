package com.zxc.find.recover.controller;

import com.zxc.find.recover.entity.Lost;
import com.zxc.find.recover.utils.Response;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author YeYuShengFan
 * @Date 2021/11/26 15:38
 */
@RestController
@RequestMapping("/lost")
public class LostController extends CRUDController<Lost> {

    @GetMapping("/get")//获取首页
    @Override
    public Response getIndex() {
        return super.getIndex();
    }

    @GetMapping("/get/{id}")//根据id获取(查看详细信息)
    @Override
    public Response getById(@PathVariable Integer id) {
        return super.getById(id);
    }

    @PostMapping("/add")//添加新的Lost信息
    @Override
    public Response add(@RequestBody Lost lost, HttpServletRequest request) {
        return super.add(lost, request);
    }

    @PutMapping("/update")//修改Lost
    @Override
    public Response update(@RequestBody Lost lost) {
        return super.update(lost);
    }

    @PostMapping("/begin")//开始找回失物
    @Override
    public Response begin(@RequestBody Lost lost, HttpServletRequest request) {
        return super.begin(lost, request);
    }

    @PostMapping("/finish")//完成找回失物
    @Override
    public Response finish(@RequestBody Lost lost) {
        return super.finish(lost);
    }

    @PostMapping("/cancel")//取消找回失物
    @Override
    public Response cancel(@RequestBody Lost lost) {
        return super.cancel(lost);
    }

    @GetMapping("/delete/{id}")//删除寻物信息
    @Override
    public Response delete(@PathVariable Integer id) {
        return super.delete(id);
    }
}