package com.pandawork.nenu.user;

import com.pandawork.nenu.AbstractTestCase;
import com.pandawork.nenu.oa.common.util.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Test on 14-9-16.
 */
public class RoleTest extends AbstractTestCase{

    @Test
    public void testtest() {
        Date date = new Date();
        String uploadTime = DateUtils.formatDate(date, "yyyyMMdd");
        System.out.println(uploadTime);
    }
}
