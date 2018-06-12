package com.utils;

import com.bean.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 检查传入对象是否为空
 */
public class CheckNull {

    /**
     * 传入对象，判断对象属性是否为空，可以去掉可为空的属性值
     * @param obj
     * @return
     */
    public static List checkObject(Object obj,String[] notckeck)  {
        //存储为空的属性名
        List<String> nullList = new ArrayList<String>();
        Class<?> cls = null;
        try {
            cls = obj.getClass();
        } catch (NullPointerException e) {
            nullList.add("Object NullPointerException");
            return nullList;
        }
        Field[] fields = cls.getDeclaredFields();
        for(Field field:fields){
            String fieldName = field.getName();
            String strGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
//            System.out.println(fieldName+" "+strGet);
            Method methodGet = null;
            try {
                methodGet = cls.getDeclaredMethod(strGet);
                Object object = methodGet.invoke(obj);
                if (checkString(object.toString())){
                   nullList.add(fieldName);
                }
            } catch (Exception e) {
                if (!fieldName.equals("canBeNull"))
                    nullList.add(fieldName);
            }
        }
        //如果为空集合不为空并且可为空数组长度大于0，去掉重合部分
        List<Integer> remove = new LinkedList<Integer>();
        if (!nullList.isEmpty() && notckeck != null){
            for (int i =0 ; i < nullList.size() ; i++){
                for (int j = 0 ; j < notckeck.length ; j++){
                    if (nullList.get(i).equals(notckeck[j])) {
                        remove.add(i);
                    }
                }
            }
            boolean flag = true;
            for (int i:remove
                 ) {
                if (flag != true)
                    i--;
                flag = false;
                nullList.remove(i);
            }
        }
        if (nullList.size() < 1)
            return null;
        return nullList;

    }

    /**
     * 检查String 是否为空
     * @param checkString
     * @return
     */
    public static boolean checkString(String checkString){
        try {
            if (checkString.equals("null") || checkString == null || checkString.equals(""))
                return true;
        }catch (NullPointerException e){
            return true;
        }
        return false;
    }
    public static void main(String arg0[]){
//        User user = new User(1,"admin","admin",'M',0,"799957684@qq.com","null",1);
        User user = new User();
        user.setUserId(1);
        List base = checkObject(user,null);
        System.out.println(base);
    }
}