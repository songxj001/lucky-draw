package com.jk.luckydraw.controller.admin;

import com.jk.luckydraw.domain.user.UserBean;
import com.jk.luckydraw.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping("luckyUser")
    @ResponseBody
    public HashMap luckyUser(){
        return userService.findLuckUser();
    }
    /**
     * 登录
     * @param userBean
     * @param request
     * @return
     */
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String,String> login(UserBean userBean, HttpServletRequest request){
        return userService.getUserInfo(userBean,request);
    }

    /**
     * 调换登录页面
     * @return
     */
    @RequestMapping("toLoginPage")
    public String toLoginPage(){
        return "login";
    }

    /**
     * 跳转主页面
     * @return
     */
    @RequestMapping("toMain")
    public String toMain(){
        return "main";
    }
}
