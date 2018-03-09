package com.pandawork.nenu;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.unitils.spring.annotation.SpringBeanByName;
import org.unitils.spring.annotation.SpringBeanByType;

import java.util.*;

import static junit.framework.Assert.assertNotNull;

/**
 * Description: Controller测试父类 欲测试controller需要继承此类
 */
public class AbstractControllerTestCase extends AbstractTestCase {

    @Autowired
    public RequestMappingHandlerAdapter handlerAdapter;

    @Autowired
    public RequestMappingHandlerMapping handlerMapping;


    public MockHttpServletRequest request;
    public MockHttpServletResponse response;
    public MockHttpSession session;

    @Before
    public void before(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        session = new MockHttpSession();
        request.setCharacterEncoding("UTF-8");
    }

    public void showAjaxMethodTestResult(){
        try{
            assertNotNull("未找到相应方法,确认请求上下文路径和请求方法",handlerMapping.getHandler(request));
            Object handler = handlerMapping.getHandler(request).getHandler();

            handlerAdapter.handle(request, response, handler);

            System.out.println(response.getContentAsString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showNormalMethodTestResult(){
        try{
            assertNotNull("未找到相应方法,确认请求上下文路径和请求方法",handlerMapping.getHandler(request));
            Object handler = handlerMapping.getHandler(request).getHandler();
            ModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

            System.out.println("ViewName = " + modelAndView.getViewName());
            System.out.println("ModelMap = ");
            ModelMap map = modelAndView.getModelMap();
            Set<String> keys = map.keySet();

            for (String key : keys) {
                Object obj = map.get(key);
                System.out.print("\tkey = " + key + " :value = ");
                if (TestUtils.getType(obj).equals("list")) {
                    List list = (List) obj;
                    for (Object item : list) {
                        System.out.print(TestUtils.getPropertyString(item));
                    }
                } else if (TestUtils.getType(obj).equals("map")) {
                    Map map1 = (Map) obj;
                    Set<String> keys1 = map1.keySet();
                    for (String key1 : keys1) {
                        System.out.print("\tkey = " + key + " :value = ");
                        Object obj1 = map1.get(key1);
                        System.out.print(TestUtils.getPropertyString(obj1));
                    }
                } else {
                    System.out.print(TestUtils.getPropertyString(obj));
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }




}