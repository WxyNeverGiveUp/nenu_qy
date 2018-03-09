package com.pandawork.nenu.oa.web.filter;


import com.pandawork.core.framework.web.filter.AbstractConstansFilter;
import com.pandawork.nenu.oa.common.util.WebConstants;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class ConstansFilter extends AbstractConstansFilter {

    private String basePath;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getContextPath();
        String url = httpRequest.getRequestURL().toString();
        basePath = httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":"
                + httpRequest.getServerPort() + path + "/";
        //添加静态资源组件库域名
        request.setAttribute("puiWebsite", WebConstants.puiWebsite);
        request.setAttribute("uploadWebsite", WebConstants.uploadWebsite);
        //request.setAttribute("puiWebsite", basePath + "resources/pui/");
        //添加modelType参数
        Iterator iter = WebConstants.modelTypeMap.entrySet().iterator();
        Boolean flag = false;
        while (iter.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) iter.next();
            if (url.contains(entry.getKey())) {
                request.setAttribute("modelType", entry.getValue());
                if (entry.getValue() == 5 || entry.getValue() == 6 || entry.getValue() == 7) {
                    request.setAttribute("isSelected", 1);
                    flag = true;
                } else if (entry.getValue() == 3) {
                    request.setAttribute("isSelected", 99);
                    flag = true;
                }
                break;
            }
        }
        if (!flag) {
            request.setAttribute("isSelected", 0);
        }
        super.doFilter(httpRequest, response, chain);
    }

    @Override
    public String getStaticWebsite() {
        //return basePath + "resources/resources/";
        return WebConstants.staticWebsite + "resources/";
    }


    @Override
    public String getTinyWebSite() {
        return basePath + "resources/";
    }

    @Override
    public String getWebfullName() {
        return WebConstants.webFullName;
    }

    @Override
    public String getWebName() {
        return WebConstants.webName;
    }

    @Override
    public String getWebTitle() {
        return "";
    }

    @Override
    public String getWebsite() {
        return basePath;
    }

    @Override
    public void destroy() {
        //noop
    }
}
