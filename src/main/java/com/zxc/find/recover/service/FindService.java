package com.zxc.find.recover.service;

import com.zxc.find.recover.entity.Find;

import java.util.List;

/**
 * @author Miyam
 */
public interface FindService {
    public List<Find> getFindIndex();

    public Find getFindById(Integer id);
}
