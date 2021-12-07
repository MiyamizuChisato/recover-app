package com.zxc.find.recover.controller;

import com.zxc.find.recover.service.PictureService;
import com.zxc.find.recover.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author YeYuShengFan
 * @Date 2021/12/5 20:06
 */
@RestController
@RequestMapping("/picture")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @PostMapping("/headPic/{id}")
    public Response headPic(@PathVariable Integer id, MultipartFile headPic){
        int success = pictureService.headPic(id, headPic);
        if (success > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }

    @PostMapping("/articlePic/{id}")
    public Response articlePic(@PathVariable Integer id, MultipartFile articlePic){
        int success = pictureService.articlePic(id, articlePic);
        if (success > 0){
            return Response.SUCCEED();
        }
        return Response.DEFEAT();
    }
}
