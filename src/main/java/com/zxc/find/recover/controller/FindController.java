package com.zxc.find.recover.controller;

import com.zxc.find.recover.entity.Find;
import com.zxc.find.recover.utils.Response;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * @author Miyam
 */
@RestController
@RequestMapping("/find")
public class FindController extends CRUDController<Find> {

    @GetMapping("/get")  //获取首页
    @Override
    public Response getIndex() {
        return super.getIndex();
    }

    @GetMapping("/get/{id}") //根据id获取(查看详情)
    @Override
    public Response getById(@PathVariable Integer id) {
        return super.getById(id);
    }

    @PostMapping("/add") // 添加新的Find表
    @Override
    public Response add(@RequestBody Find find, HttpServletRequest request) {
        return super.add(find, request);
    }

    @PutMapping("/update") // 修改Find信息
    @Override
    public Response update(@RequestBody Find find) {
        return super.update(find);
    }

    @PostMapping("/begin") // 开始认领
    @Override
    public Response begin(@RequestBody Find find, HttpServletRequest request) {
        return super.begin(find, request);
    }

    @PostMapping("/finish") // 完成认领
    @Override
    public Response finish(@RequestBody Find find) {
        return super.finish(find);
    }

    @PostMapping("/cancel") // 取消认领
    @Override
    public Response cancel(@RequestBody Find find) {
        return super.cancel(find);
    }

    @GetMapping("/delete/{id}")//删除招领信息
    @Override
    public Response delete(@PathVariable Integer id) {
        return super.delete(id);
    }
}