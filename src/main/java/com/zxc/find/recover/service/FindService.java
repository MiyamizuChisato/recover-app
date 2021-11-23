package com.zxc.find.recover.service;

import com.zxc.find.recover.entity.Find;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Miyam
 */
public interface FindService {
    public List<Find> getFindIndex();

    public Find getFindById(Integer id);

    public int addNewFind(Find find, MultipartFile picture);

    public int updateFind(Find find);

    public void viewCountPlus(Find find);
}
