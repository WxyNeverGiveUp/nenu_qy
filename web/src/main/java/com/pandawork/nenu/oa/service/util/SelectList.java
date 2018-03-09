package com.pandawork.nenu.oa.service.util;

import com.pandawork.core.common.exception.SSException;
import net.sf.json.JSONArray;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: wanghq
 * Date: 2014/9/26
 * Time: 8:10
 */
public interface SelectList {

    /**
     * 获取Service对应实体的列表返回一个JSON数组 [{id:xxx,name:xxx},{id:xxx,name:xxx},{id:xxx,name:xxx}...]
     */

    public JSONArray getEntityList(Integer...params) throws SSException;
}
