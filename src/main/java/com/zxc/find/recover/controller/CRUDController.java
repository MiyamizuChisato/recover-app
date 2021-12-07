package com.zxc.find.recover.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.zxc.find.recover.entity.Article;
import com.zxc.find.recover.entity.Type;
import com.zxc.find.recover.service.CRUDService;
import com.zxc.find.recover.utils.JwtUtils;
import com.zxc.find.recover.utils.Response;
import com.zxc.find.recover.utils.TypeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author YeYuShengFan
 * @Date 2021/12/5 20:23
 */
public class CRUDController<T> {
    @Autowired
    private CRUDService<T> service;
    //获取Find和Lost首页信息
    public Response getIndex(){
        List<T> index = service.getIndex();
        if (!index.isEmpty()){
            //对首页的find或者lost进行分类
            List<T> electronic = new ArrayList<>();
            List<T> daily = new ArrayList<>();
            List<T> study = new ArrayList<>();
            List<T> sport = new ArrayList<>();
            List<T> other = new ArrayList<>();
            for (T t : index) {
                //通过反射获取到物品具体类型的id
                Integer articleTypeId = null;
                try {
                    Method getArticle = t.getClass().getDeclaredMethod("getArticle");
                    Article article = (Article)getArticle.invoke(t);
                    Method getType = article.getClass().getDeclaredMethod("getType");
                    Type type = (Type)getType.invoke(article);
                    Method getId = type.getClass().getDeclaredMethod("getId");
                    articleTypeId = (Integer)getId.invoke(type);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                //通过获取到的物品类型id分类
                switch (articleTypeId) {
                    case 1: electronic.add(t); break;
                    case 2: daily.add(t); break;
                    case 3: study.add(t); break;
                    case 4: sport.add(t);break;
                    case 5: other.add(t); break;
                    default: break;
                }
            }
            //给分好类的失物的集合创建种类名字
            TypeItem<T> eleItem = new TypeItem<>();
            TypeItem<T> dailyItem = new TypeItem<>();
            TypeItem<T> studyItem = new TypeItem<>();
            TypeItem<T> sportItem = new TypeItem<>();
            TypeItem<T> otherItem = new TypeItem<>();
            eleItem.setName("电子产品");
            eleItem.setItemList(electronic);
            dailyItem.setName("日常用品");
            dailyItem.setItemList(daily);
            studyItem.setName("学习用品");
            studyItem.setItemList(study);
            sportItem.setName("体育器材");
            sportItem.setItemList(sport);
            otherItem.setName("其他");
            otherItem.setItemList(other);
            List<TypeItem<T>> typeList = new ArrayList<>();
            typeList.add(eleItem);
            typeList.add(dailyItem);
            typeList.add(studyItem);
            typeList.add(sportItem);
            typeList.add(otherItem);
            //将分好类的物品列表返回给前端
            return Response.SUCCEED().carry("typeList",typeList);
        }
        return Response.DEFEAT();
    }

    public Response getById(Integer id){
        T infoById = service.getInfoById(id);
        if (infoById != null){
            return Response.SUCCEED().carry("info",infoById);
        }
        return Response.DEFEAT();
    }

    public Response add(T t, HttpServletRequest request){
        //获取用户id
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        Integer userId = Integer.parseInt(verity.getClaim("userId").asString());
        //设置失物招领的发起人
        try {
            t.getClass().getDeclaredMethod("setStartUserId", Integer.class).invoke(t,userId);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        int articleId = service.addNewInfo(t);
        if (articleId > 0){
            return Response.SUCCEED().carry("articleId",articleId);
        }
        return Response.DEFEAT();
    }

    public Response update(T t){
        int update = service.updateInfo(t);
        if (update > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    public Response begin(T t,HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verity = JwtUtils.verity(token);
        Integer userId = Integer.parseInt(verity.getClaim("userId").asString());
        //设置失物招领的结束人
        try {
            Method setEndUserId = t.getClass().getDeclaredMethod("setEndUserId", Integer.class);
            setEndUserId.invoke(t,userId);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        int begin = service.updateInfo(t);
        if (begin > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    public Response finish(T t){
        //若已完成失物招领，则将设置对应信息的是否完成和结束时间字段
        Timestamp timestamp = new Timestamp(new Date().getTime());
        try {
            t.getClass().getDeclaredMethod("setClaim",Integer.class).invoke(t,1);
            t.getClass().getDeclaredMethod("setEndTime",Timestamp.class).invoke(t,timestamp);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        int finish = service.updateInfo(t);
        if (finish > 0) {
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    public Response cancel(T t){
        //取消失物招领，将对应信息的相关字段设置为初始状态
        try {
            t.getClass().getDeclaredMethod("setClaim",Integer.class).invoke(t,0);
            t.getClass().getDeclaredMethod("setEndTime",Timestamp.class).invoke(t,null);
            t.getClass().getDeclaredMethod("setEndUserId", Integer.class).invoke(t,0);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        int cancel = service.updateInfo(t);
        if (cancel > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    public Response delete(Integer id){
        T infoById = service.getInfoById(id);
        int delete = service.deleteInfo(infoById);
        if (delete > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }
}
