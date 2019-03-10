package com.jk.luckydraw.controller.jkjw;

import com.alibaba.fastjson.JSONArray;
import com.jk.luckydraw.domain.jkjw.StudentDisciplinetBean;
import com.jk.luckydraw.service.jkjw.JkjwService;
import com.jk.luckydraw.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
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

    @RequestMapping("queryWjl")
    public String queryWjl(String callback){
        List<Map> map = jkjwService.queryWjl();
        String result = JSONArray.toJSONString(map);
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
        Map map = jkjwService.saveStudentDisciplinet(studentDisciplinetBean);
        String result = JSONArray.toJSONString(map);
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
    @ResponseBody
    public HashMap<String, Object> upload(HttpServletRequest request, HttpServletResponse resp, HttpSession session) {
        HashMap<String, Object> result = new HashMap<>();
        List<Object> list = new ArrayList<Object>();
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
        String result = JSONArray.toJSONString(list);
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
        String result = JSONArray.toJSONString(maps);
        //用回调函数名称包裹返回数据，这样，返回数据就作为回调函数的参数传回去了
        result = callback + "(" + result + ")";
        return result;
    }
}
