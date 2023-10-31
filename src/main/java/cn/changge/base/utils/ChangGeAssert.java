package cn.changge.base.utils;

import cn.changge.base.enums.ResponseCode;
import cn.changge.base.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

/**
 * 断言工具类
 */
public class ChangGeAssert {


    // StringUtils.isBlank  StringUtils.isEmpty的区别
    // StringUtils.isEmpty()只判断字符串是否为空和空字符串，如果字符串是"    "它认为它不是空字符串的
    // StringUtils.isBlank()不仅判断字符串是否为空和空字符串，而且会判断其中的每一个字符是否是空字符串，如果字符串是"   "，
    // 它也会认为是空字符串
    // 断言字符串为空
    public static void isBlank(String str,String message){
        // 如果字符串不为空，就抛出异常
        if(StringUtils.isNotBlank(str)){
            throw new BusinessException(message);
        }
    }

    // 断言字符串不为空
    public static void isNotBlank(String str,String message){
        // 如果字符串为空，就抛出异常
        if(StringUtils.isBlank(str)){
            throw new BusinessException(message);
        }
    }

    // 断言对象为空
    public static void isNull(Object obj,String message){
        // 如果对象不为空，就抛出异常
        if(Objects.nonNull(obj)){
            throw new BusinessException(message);
        }
    }

    // 断言对象不为空
    public static void isNotNull(Object obj,String message){
        // 如果对象为空，就抛出异常
        if(Objects.isNull(obj)){
            throw new BusinessException(message);
        }
    }

    // 断言集合为空
    public static void isNull(Collection collection, String message){
        // 如果集合不为空，就抛出异常
        if(collection != null && collection.size() > 0){
            throw new BusinessException(message);
        }
    }

    // 断言集合不为空
    public static void isNotNull(Collection collection, String message){
        // 如果集合为空，就抛出异常
        if(collection == null || collection.size() == 0){
            throw new BusinessException(message);
        }
    }

    // 断言两个字符串值相等
    public static void isEq(String str1,String str2,String message){
        // 如果两个字符串不相等，就抛出异常
        if(!str1.equals(str2)){
            throw new BusinessException(message);
        }
    }

    //====================================================================

    public static void isBlank(String str,ResponseCode code){
        // 如果字符串不为空，就抛出异常
        if(StringUtils.isNotBlank(str)){
            throw new BusinessException(code);
        }
    }

    // 断言字符串不为空
    public static void isNotBlank(String str,ResponseCode code){
        // 如果字符串为空，就抛出异常
        if(StringUtils.isBlank(str)){
            throw new BusinessException(code);
        }
    }

    // 断言对象为空
    public static void isNull(Object obj,ResponseCode code){
        // 如果对象不为空，就抛出异常
        if(Objects.nonNull(obj)){
            throw new BusinessException(code);
        }
    }

    // 断言对象不为空
    public static void isNotNull(Object obj,ResponseCode code){
        // 如果对象为空，就抛出异常
        if(Objects.isNull(obj)){
            throw new BusinessException(code);
        }
    }

    // 断言集合为空
    public static void isNull(Collection collection, ResponseCode code){
        // 如果集合不为空，就抛出异常
        if(collection != null && collection.size() > 0){
            throw new BusinessException(code);
        }
    }

    // 断言集合不为空
    public static void isNotNull(Collection collection,ResponseCode code){
        // 如果集合为空，就抛出异常
        if(collection == null || collection.size() == 0){
            throw new BusinessException(code);
        }
    }

    // 断言两个字符串值相等
    public static void isEq(String str1,String str2,ResponseCode code){
        // 如果两个字符串不相等，就抛出异常
        if(!str1.equals(str2)){
            throw new BusinessException(code);
        }
    }
}