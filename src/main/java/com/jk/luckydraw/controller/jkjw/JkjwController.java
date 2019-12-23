package com.jk.luckydraw.controller.jkjw;

import com.alibaba.fastjson.JSONArray;
import com.jk.luckydraw.domain.jkjw.ResultBean;
import com.jk.luckydraw.domain.jkjw.StudentDisciplinetBean;
import com.jk.luckydraw.domain.user.UserBean;
import com.jk.luckydraw.service.jkjw.JkjwService;
import com.jk.luckydraw.service.user.UserService;
import com.jk.luckydraw.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("jkjw")
public class JkjwController {


    @Value("${img.location}")
    private String location;

    @Value("${img.serverpath}")
    private String serverpath;

    @Autowired
    private JkjwService jkjwService;

    @Autowired
    private UserService userService;

    /**
     * 学生登录
     * @param phone
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("studentLogin")
    public Map studentLogin(String phone,String password,HttpSession session){
        return jkjwService.queryStudentWjList(phone,password,session);
    }

    /**
     * 学生登录发送短信验证码
     * @param phone
     * @param session
     * @return
     */
    @GetMapping("sendStudentLoginCode")
    public Boolean sendStudentLoginCode(String phone,HttpSession session){
        session.setAttribute(phone,"888888");
        return true;
    }

    /**
     * 查询月度每天违纪量
     * @return
     */
    @RequestMapping("queryMouthsWjl")
    public String queryMouthsWjl(String callback){
        ResultBean ok = ResultBean.ok();
        List<Map> map = jkjwService.queryMouthsWjl();
        ok.put("data",map);
        String result = JSONArray.toJSONString(ok);
        //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
        result = callback + "(" + result + ")";
        return result;
    }

    /**
     * 登录
     * @param callback
     * @param userBean
     * @param request
     * @return
     */
    @RequestMapping("login")
    public String login(String callback, UserBean userBean, HttpServletRequest request){
        HashMap<String, String> userInfo = userService.getUserInfo(userBean, request);
        String result = JSONArray.toJSONString(userInfo);
        //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
        result = callback + "(" + result + ")";
        return result;
    }
    /**
     * 查询年度违纪量
     * @param callback
     * @return
     */
    @RequestMapping("queryWjl")
    public String queryWjl(String callback){
        ResultBean ok = ResultBean.ok();
        List<Map> map = jkjwService.queryWjl();
        ok.put("data",map);
        String result = JSONArray.toJSONString(ok);
        //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
        result = callback + "(" + result + ")";
        return result;
    }

    /**
     * 保存违纪学生信息并扣分
     * @param studentDisciplinetBean
     * @param callback
     * @return
     */
    @RequestMapping("saveStudentDisciplinet")
    public String saveStudentDisciplinet(StudentDisciplinetBean studentDisciplinetBean,String callback){
        ResultBean ok = ResultBean.ok();
        Map map = jkjwService.saveStudentDisciplinet(studentDisciplinetBean);
        ok.putAll(map);
        String result = JSONArray.toJSONString(ok);
        //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
        result = callback + "(" + result + ")";
        return result;
    }

    /**
     * 违纪照片上传
     * @param request
     * @param resp
     * @param session
     * @return
     */
    @RequestMapping("upload")
    public HashMap<String, Object> upload(HttpServletRequest request, HttpServletResponse resp, HttpSession session) {
        HashMap<String, Object> result = new HashMap<>();
        if (!StringUtils.isEmpty(request.getParameter("base64data"))) {
            String base64data = request.getParameter("base64data");
            System.out.println(base64data);
            // Object r = this.uploadtolocal(request.getParameter("base64data"));
            //list.add(r);
            result.put("code",1);
            result.put("imgName","上传失败");
        } else {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            String fileUpload = "";
            Map<String, MultipartFile> o = multipartRequest.getFileMap();
            if (o.size() > 0) {
                MultipartFile multipartFile = null;
                for (String k : o.keySet()) {
                    multipartFile = o.get(k);
                    fileUpload = FileUtil.FileUpload(multipartFile, request, location);
                }
                result.put("code",0);
                result.put("imgName",serverpath+fileUpload);
            }
        }
        return result;
    }

    /**
     * 查询违纪列表
     * @param callback
     * @return
     */
    @RequestMapping("queryWjList")
    public String queryWjList(String callback){
        List<Map> list = jkjwService.queryWjList();
        ResultBean ok = ResultBean.ok();
        ok.put("data",list);
        String result = JSONArray.toJSONString(ok);
        //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
        result = callback + "(" + result + ")";
        return result;
    }
    /**
     * 查询班级列表
     * @param callback
     * @return
     */
    @RequestMapping("queryClassList")
    public String queryClassList(String callback){

        List<Map> maps = jkjwService.queryClassList();
        ResultBean ok = ResultBean.ok();
        ok.put("data",maps);
        String result = JSONArray.toJSONString(ok);
        //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
        result = callback + "(" + result + ")";
        return result;
    }
}
