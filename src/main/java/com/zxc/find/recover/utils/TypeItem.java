package com.zxc.find.recover.utils;

import com.zxc.find.recover.entity.Find;
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
public class TypeItem {
    private String name;
    private List<Find> itemList;
}
