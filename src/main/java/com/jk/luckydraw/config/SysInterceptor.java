package com.jk.luckydraw.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jk.luckydraw.domain.jkjw.CheckResult;
import com.jk.luckydraw.domain.jkjw.ResultBean;
import com.jk.luckydraw.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class SysInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(SysInterceptor.class);
	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  
            throws Exception {
        String callback = request.getParameter("callback");
        if (handler instanceof HandlerMethod){
    		String authHeader = request.getParameter("token");
        	if (StringUtils.isEmpty(authHeader)) {
        	  logger.info("验证失败");
        	  print(response,ResultBean.error(ConstantConf.JWT_ERRCODE_NULL,"签名验证不存在"),callback);
              return false;
            }else{
            	//验证JWT的签名，返回CheckResult对象
                CheckResult checkResult = JwtUtils.validateJWT(authHeader);
                if (checkResult.isSuccess()) {
                	return true;
                } else {
                    switch (checkResult.getErrCode()) {
                    // 签名验证不通过
                    case ConstantConf.JWT_ERRCODE_FAIL:
                    	logger.info("签名验证不通过");
                    	print(response,ResultBean.error(checkResult.getErrCode(),"签名验证不通过"),callback);
                    	break;
                     // 签名过期，返回过期提示码
                    case ConstantConf.JWT_ERRCODE_EXPIRE:
                    	logger.info("签名过期");
                    	print(response,ResultBean.error(checkResult.getErrCode(),"签名过期"),callback);
                    	break;
                    default:
                        break;
                    }
                    return false;
                }
            }
		}else{
			return true;
		}
    }  
    public void print(HttpServletResponse response,Object message,String callback){
    	try {
			response.setStatus(HttpStatus.OK.value());
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			PrintWriter writer = response.getWriter();
			String result = JSONObject.toJSONString(message);
            //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
            result = callback + "(" + result + ")";
			writer.write(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,  
                           ModelAndView modelAndView) throws Exception {  
        if(response.getStatus()==500){  
            modelAndView.setViewName("/error/500");  
        }else if(response.getStatus()==404){  
            modelAndView.setViewName("/error/404");  
        }  
    }  
  
    /**  
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，  
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。  
     */    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
    }  
}
