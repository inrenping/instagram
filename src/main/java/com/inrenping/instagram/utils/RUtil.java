package com.inrenping.instagram.utils;

import com.inrenping.instagram.entity.vo.R;

/**
 * 对象模型工具类
 */
public class RUtil {
    public static R success(Object object){
        R r = new R();
        r.setCode(0);
        r.setMsg("ok");
        r.setData(object);
        return r;
    }
    public static R success(){
        return success(null);
    }

    public static R error(Integer code, String msg){
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
