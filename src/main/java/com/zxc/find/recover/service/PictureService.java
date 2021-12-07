package com.zxc.find.recover.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author YeYuShengFan
 * @Date 2021/12/5 20:09
 */
@Service
public interface PictureService {
    public int articlePic(Integer id, MultipartFile articlePic);

    public int headPic(Integer id,MultipartFile headPic);

    public String randomFileName(MultipartFile picture);
}
