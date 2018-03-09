package com.pandawork.nenu;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * User: baobaoyeye
 * Version:ds-0.01
 * Date: 13-7-23
 * Time: 上午10:01
 * Description:测试工具类
 */
public class TestUtils {

    /**
     * 获取给定对象的属性名值对
     * @param entityName
     * @return
     * @throws Exception
     */
    public static String getPropertyString(Object entityName) throws Exception {
        Class c = entityName.getClass();
        Field field [] = c.getDeclaredFields();
        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        for(Field f : field){
            sb.append("\t\t").append(f.getName());
            sb.append(" : ");
            sb.append(invokeMethod(entityName,f.getName(),null));
            sb.append("\n");
        }
        sb.append("------ " + " end ------\n");
        return sb.toString();
    }

    /**
     * 反射调用指定对象的某个方法
     * @param owner
     * @param methodName
     * @param args
     * @return
     * @throws Exception
     */
    public static Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception{
        Class ownerClass = owner.getClass();

        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
        Method method = null;
        try {
            method = ownerClass.getMethod("get" + methodName);
        } catch (SecurityException e) {
            //e.printStackTrace();
        } catch (NoSuchMethodException e) {
            //e.printStackTrace();
            return " can't find 'get" + methodName + "' method";
        }

        return method != null ? method.invoke(owner) : null;
    }

    /**
     * 获取对象类型，判断是否为list或者是map类型
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String getType(T t){
        if(t instanceof List){
            return "list";
        }else if(t instanceof Map){
            return "map";
        }else{
            return "simple";
        }
    }
}
