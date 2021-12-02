package com.zxc.find.recover.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxc.find.recover.entity.Lost;
import com.zxc.find.recover.service.impl.LostServiceImpl;
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
 * @Author YeYuShengFan
 * @Date 2021/11/26 15:38
 */
@RestController
@RequestMapping("/lost")
public class LostController {
    @Autowired
    private LostServiceImpl lostService;

    @GetMapping("/get")//首页获取失物信息并分类
    public Response getLostIndex(){
        List<Lost> lostIndex = lostService.getLostIndex();
        if (!lostIndex.isEmpty()){
            //对不同种类的失物进行分类
            List<Lost> eleLost = new ArrayList<>();
            List<Lost> dailyLost = new ArrayList<>();
            List<Lost> studyLost = new ArrayList<>();
            List<Lost> sportLost = new ArrayList<>();
            List<Lost> otherLost = new ArrayList<>();
            for (Lost lost : lostIndex) {
                Integer typeId = lost.getArticle().getType().getId();
                switch (typeId) {
                    case 1: eleLost.add(lost); break;
                    case 2: dailyLost.add(lost); break;
                    case 3: studyLost.add(lost); break;
                    case 4: sportLost.add(lost);break;
                    case 5: otherLost.add(lost); break;
                    default: break;
                }
            }
            //给分好类的失物的集合创建种类名字
            TypeItem<Lost> eleItem = new TypeItem<>();
            TypeItem<Lost> dailyItem = new TypeItem<>();
            TypeItem<Lost> studyItem = new TypeItem<>();
            TypeItem<Lost> sportItem = new TypeItem<>();
            TypeItem<Lost> otherItem = new TypeItem<>();
            eleItem.setName("电子产品");
            eleItem.setItemList(eleLost);
            dailyItem.setName("日常用品");
            dailyItem.setItemList(dailyLost);
            studyItem.setName("学习用品");
            studyItem.setItemList(studyLost);
            sportItem.setName("体育器材");
            sportItem.setItemList(sportLost);
            otherItem.setName("其他");
            otherItem.setItemList(otherLost);
            List<TypeItem<Lost>> typeList = new ArrayList<>();
            typeList.add(eleItem);
            typeList.add(dailyItem);
            typeList.add(studyItem);
            typeList.add(sportItem);
            typeList.add(otherItem);
            return Response.SUCCEED().carry("typeList",typeList);
        }
        return Response.DEFEAT();
    }

    @GetMapping("/get/{id}")//根据id获取失物信息
    public Response getLostById(@PathVariable Integer id){
        Lost lost = lostService.getLostById(id);
        if (lost != null){
            return Response.SUCCEED().carry("lost",lost);
        }
        return Response.DEFEAT();
    }

    @PostMapping("/addLost")//添加新的寻物启事
    public Response addLost(@RequestBody Lost lost, MultipartFile picture, HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        Integer userId = Integer.parseInt(verity.getClaim("userId").asString());
        lost.setStartUserId(userId);
        int i = lostService.addLost(lost, picture);
        if (i > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PutMapping("/updateLost")//修改寻物启事
    private Response updateLost(@RequestBody Lost lost){
        int update = lostService.updateLost(lost);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/beginRecover")//开始找回失物
    public Response beginRecover(@RequestBody Lost lost,HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        Integer userId = Integer.parseInt(verity.getClaim("userId").asString());
        lost.setEndUserId(userId);//开始找回失物，暂时只设置领取该失物的人的id
        int update = lostService.updateLost(lost);
        if (update > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/finishRecover")//完成找回失物
    public Response finishRecover(@RequestBody Lost lost){
        Lost finishLost = lostService.getLostById(lost.getId());
        finishLost.setClaim(1);
        Timestamp timestamp = new Timestamp(new Date().getTime());
        finishLost.setEndTime(timestamp);//完成找回失物/确定失物已找回，设置寻物启事表的状态和结束时间
        int update = lostService.updateLost(finishLost);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/cancelRecover")//取消找回失物
    private Response cancelRecover(@RequestBody Lost lost) {
        Lost cancelLost = lostService.getLostById(lost.getId());
        cancelLost.setClaim(0);
        cancelLost.setEndTime(null);
        cancelLost.setEndUserId(0);
        int update = lostService.updateLost(cancelLost);
        if (update > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @GetMapping("/deleteLost/{id}")//删除寻物启事
    public Response deleteRecover(@PathVariable Integer id){
        Lost lost = lostService.getLostById(id);
        int delete = lostService.deleteLost(lost);
        if (delete > 0){
            return  Response.SUCCEED();
        }
        return Response.DEFEAT();
    }
}