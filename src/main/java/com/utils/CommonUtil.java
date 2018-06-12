package com.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CommonUtil {

	/**
	 * @author jipeng
	 * @return
	 * 
	 *         构造返回json
	 * 
	 */
	public static JSONObject constructResponse(int code, String msg, Object data) {
		JSONObject jo = new JSONObject();
		jo.put("code", code);
		jo.put("msg", msg);
		jo.put("data", data);
		return jo;
	}

	/**
	 * 错误返回
	 * @param e
	 * @return
	 */
	public static JSONObject error(Exception e) {
		JSONObject jo = new JSONObject();
		jo.put("code", 0);
		if (!CheckNull.checkString(e.getMessage()))
			jo.put("msg", e.getMessage());
		jo.put("msg", "未知异常");
		jo.put("data", null);
		return jo;
	}

	/**
	 * 错误返回
	 * @param e
	 * @return
	 */
	public static JSONObject error(String  e) {
		JSONObject jo = new JSONObject();
		jo.put("code", 0);
		jo.put("msg", e);
		jo.put("data", null);
		return jo;
	}
	public static JSONObject error() {
		JSONObject jo = new JSONObject();
		jo.put("code", 0);
		jo.put("msg", "操作失败");
		jo.put("data", null);
		return jo;
	}
	/**
	 * 成功返回
	 * @param
	 * @return
	 */
	public static JSONObject success(Object object) {
		JSONObject jo = new JSONObject();
		jo.put("code", 1);
		jo.put("msg", "成功");
		jo.put("data", object);
		return jo;
	}

	/**
	 * 未登录返回
	 * @param
	 * @return
	 */
	public static JSONObject notLoign() {
		JSONObject jo = new JSONObject();
		jo.put("code", 0);
		jo.put("msg", "用户未登录");
		jo.put("data", null);
		return jo;
	}

    
    
}
