package com.pandawork.nenu.oa.common.util;

/**
 * Created by root on 14-10-16.
 */
import com.pandawork.nenu.oa.common.entity.menu.Resource;

import java.util.Comparator;

public class CompareUtil implements Comparator {

    public int compare(Object o1, Object o2) {
        // TODO Auto-generated method stub
        Resource c1 = (Resource) o1;
        Resource c2 = (Resource) o2;
        if (c1.getId() > c2.getId()) {
            return 1;
        } else {
            if (c1.getId() == c2.getId()) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
