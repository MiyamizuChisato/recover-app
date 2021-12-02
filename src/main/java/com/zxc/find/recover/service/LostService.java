package com.zxc.find.recover.service;

import com.zxc.find.recover.entity.Lost;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author YeYuShengFan
 * @Date 2021/11/26 15:39
 */
public interface LostService {
    public List<Lost> getLostIndex();

    public Lost getLostById(Integer id);

    public int addLost(Lost lost, MultipartFile picture);

    public int updateLost(Lost lost);

    public int deleteLost(Lost lost);

    public void viewCountPlus(Lost lost);
}