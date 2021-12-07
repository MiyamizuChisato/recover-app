package com.zxc.find.recover.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Miyam
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeItem<T> {
    private String name;
    private List<T> itemList;
}
