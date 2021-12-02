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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Miyam
 */
@RestController
@RequestMapping("/find")
public class FindController {
    @Autowired
    private FindServiceImpl findService;

    @GetMapping("/get")  //首页
    public Response getFindIndex() {
        List<Find> findIndex = findService.getFindIndex();
        if (!findIndex.isEmpty()){
            //对不同种类的失物进行分类
            List<Find> eleFind = new ArrayList<>();
            List<Find> dailyFind = new ArrayList<>();
            List<Find> studyFind = new ArrayList<>();
            List<Find> sportFind = new ArrayList<>();
            List<Find> otherFind = new ArrayList<>();
            for (Find find : findIndex) {
                Integer typeId = find.getArticle().getType().getId();
                switch (typeId) {
                    case 1: eleFind.add(find); break;
                    case 2: dailyFind.add(find); break;
                    case 3: studyFind.add(find); break;
                    case 4: sportFind.add(find);break;
                    case 5: otherFind.add(find); break;
                    default: break;
                }
            }
            //给分好类的招领的集合创建种类名字
            TypeItem<Find> eleItem = new TypeItem<>();
            TypeItem<Find> dailyItem = new TypeItem<>();
            TypeItem<Find> studyItem = new TypeItem<>();
            TypeItem<Find> sportItem = new TypeItem<>();
            TypeItem<Find> otherItem = new TypeItem<>();
            eleItem.setName("电子产品");
            eleItem.setItemList(eleFind);
            dailyItem.setName("日常用品");
            dailyItem.setItemList(dailyFind);
            studyItem.setName("学习用品");
            studyItem.setItemList(studyFind);
            sportItem.setName("体育器材");
            sportItem.setItemList(sportFind);
            otherItem.setName("其他");
            otherItem.setItemList(otherFind);
            List<TypeItem<Find>> typeList = new ArrayList<>();
            typeList.add(eleItem);
            typeList.add(dailyItem);
            typeList.add(studyItem);
            typeList.add(sportItem);
            typeList.add(otherItem);
            return Response.SUCCEED().carry("typeList",typeList);
        }
        return Response.DEFEAT();
    }

    @GetMapping("/get/{id}") // 根据id获取
    public Response getFindById(@PathVariable Integer id) {
        Find find = findService.getFindById(id);
        if (find != null) {
            return Response.SUCCEED().carry("find", find);
        }
        return Response.DEFEAT();
    }

    @PostMapping("/addFind") // 添加新的Find
    public Response addFind(@RequestBody Find find, MultipartFile picture, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        Integer userId = Integer.parseInt(verity.getClaim("userId").asString());
        find.setStartUserId(userId);
        int i = findService.addNewFind(find, picture);
        if (i > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PutMapping("/updateFind") // 修改Find
    public Response updateFind(@RequestBody Find find) {
        int update = findService.updateFind(find);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/beginRecognize") // 开始认领
    public Response startFind(@RequestBody Find find, HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        Integer userId = Integer.parseInt(verity.getClaim("userId").asString());
        find.setEndUserId(userId);
        int update = findService.updateFind(find);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/finishRecognize") // 完成认领
    public Response endFind(@RequestBody Find find) {
        Find finishFind = findService.getFindById(find.getId());
        finishFind.setClaim(1);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        finishFind.setEndTime(timestamp);//完成招领，设置招领启事表的状态和结束时间
        int update = findService.updateFind(finishFind);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/cancelRecognize") // 取消认领
    private Response cancelFind(@RequestBody Find find) {
        Find cancelFind = findService.getFindById(find.getId());
        cancelFind.setClaim(0);
        cancelFind.setEndTime(null);
        cancelFind.setEndUserId(0);
        int update = findService.updateFind(cancelFind);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @GetMapping("/deleteFind/{id}")//删除招领启示
    public Response deleteRecover(@PathVariable Integer id){
        Find find = findService.getFindById(id);
        int delete = findService.deleteFind(find);
        if (delete > 0){
            return  Response.SUCCEED();
        }
        return Response.DEFEAT();
    }
}