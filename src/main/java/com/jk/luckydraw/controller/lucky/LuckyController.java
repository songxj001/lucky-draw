package com.jk.luckydraw.controller.lucky;

import com.jk.luckydraw.domain.user.BaoMingUserBean;
import com.jk.luckydraw.domain.user.LuckyUserBean;
import com.jk.luckydraw.service.lucky.LuckyUserService;
import com.jk.luckydraw.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("lucky")
public class LuckyController {

    @Autowired
    private LuckyUserService luckyUserService;


    @RequestMapping("saveBaoMingUser")
    @ResponseBody
    public HashMap saveBaoMingUser(BaoMingUserBean baoMingUserBean, HttpServletRequest request){
        System.out.println("1");
        String userAgent = request.getHeader("user-agent");
        if (userAgent.indexOf("MicroMessenger") > 0){
            return luckyUserService.saveBaoMingUser(baoMingUserBean,request);
        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("code",3);
            result.put("msg","请使用微信客户端打开");
            result.put("icon",0);
            return result;
        }
    }

    /**
     * 保存参与人员信息
     * @param luckyUserBean
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public HashMap save(LuckyUserBean luckyUserBean,@RequestParam("imgFile") MultipartFile file, HttpServletRequest request){
        String userAgent = request.getHeader("user-agent");
        if (userAgent.indexOf("MicroMessenger") > 0){
            return luckyUserService.save(luckyUserBean,file,request);
        }else{
            HashMap<String, Object> result = new HashMap<>();
            result.put("code",3);
            result.put("msg","请使用微信客户端打开");
            result.put("icon",0);
            return result;
        }
    }

    /**
     * 跳转报名页面
     * @return
     */
    @RequestMapping("baoming")
    public String toBaoming(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent");
        if (userAgent.indexOf("MicroMessenger") > 0){
            return "baoming";
        }else{
            return "error";
        }
    }

    /**
     * 跳转注册抽奖用户信息页面
     * @return
     */
    @RequestMapping("reg")
    public String toReg(HttpServletRequest request){
        String userAgent = request.getHeader("user-agent");
        if (userAgent.indexOf("MicroMessenger") > 0){
            return "reg";
        }else{
            return "error";
        }
    }
}
