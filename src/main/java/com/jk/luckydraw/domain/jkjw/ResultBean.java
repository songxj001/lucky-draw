package com.jk.luckydraw.domain.jkjw;

import java.util.HashMap;
import java.util.Map;

public class ResultBean extends HashMap<String, Object>{

	private static final long serialVersionUID = 1L;
	

	public ResultBean() {
		put("code", 0);
	}
	
	public static ResultBean error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static ResultBean error(String msg) {
		return error(500, msg);
	}
	
	public static ResultBean error(int code, String msg) {
		ResultBean r = new ResultBean();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ResultBean ok(String msg) {
		ResultBean r = new ResultBean();
		r.put("code", 0);
		r.put("msg", msg);
		return r;
	}
	
	public static ResultBean ok(Map<String, Object> map) {
		ResultBean r = new ResultBean();
		r.put("code", 0);
		r.put("msg", "成功");
		r.putAll(map);
		return r;
	}
	
	public static ResultBean ok() {
		ResultBean r = new ResultBean();
		r.put("code", 0);
		r.put("msg", "成功");
		return new ResultBean();
	}

	public ResultBean put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
