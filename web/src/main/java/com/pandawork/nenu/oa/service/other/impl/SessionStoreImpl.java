package com.pandawork.nenu.oa.service.other.impl;/*package com.onlineTutor.service.other.impl;

import com.onlineTutor.service.other.SessionStore;
import com.pandawork.core.common.cache.impl.LocalCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

*//**
 * LRU的session存储
 * Created by lix on 2015/12/11.
 *//*
@Service("lruMemStore")
public class SessionStoreImpl implements SessionStore{
    ConcurrentHashMap<String, String> userSessionIdStore  =  new ConcurrentHashMap<String,String>();

    @Override
    public String findSessionId(String username) {
        String sessionId =  userSessionIdStore.get(username);
        return sessionId;
    }

    @Override
    public boolean removeSessionId(String username) {
       String sessionId = userSessionIdStore.remove(username);
        if (null == sessionId) {
            return false;
        }
        return true;
    }

    @Override
    public void saveSessionId(String username,String sessionId) {
        if (null != sessionId) {
            userSessionIdStore.put(username, sessionId);
        }
    }

    @Override
    public void forceLogoutUser(String username) {
        // 删除单一登录中记录的变量
        if (SessionListener.sessionMap.get(uid) != null) {
            HttpSession hs = (HttpSession) SessionListener.sessionMap.get(uid);
            FrontSess.sessionMap.remove(uid);
            Enumeration e = hs.getAttributeNames();
            while (e.hasMoreElements()) {
                String sessionName = (String) e.nextElement();
                // 清空session
                hs.removeAttribute(sessionName);
            }
            // hs.invalidate();
        }
    }
}*/
