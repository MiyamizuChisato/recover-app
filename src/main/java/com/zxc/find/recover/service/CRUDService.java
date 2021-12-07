package com.zxc.find.recover.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author YeYuShengFan
 * @Date 2021/12/7 18:29
 */
@Service
public interface CRUDService<T> {
    public List<T> getIndex();

    public T getInfoById(Integer id);

    public int addNewInfo(T entity);

    public int updateInfo(T entity);

    public int deleteInfo(T entity);

    public void viewCountPlus(T entity);
}
