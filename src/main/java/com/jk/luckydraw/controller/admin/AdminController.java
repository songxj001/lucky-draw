package com.jk.luckydraw.controller.admin;

import com.jk.luckydraw.domain.lucky.LuckyHistoryBean;
import com.jk.luckydraw.domain.prize.PrizeBean;
import com.jk.luckydraw.domain.user.LuckyUserBean;
import com.jk.luckydraw.domain.user.UserBean;
import com.jk.luckydraw.mapper.prize.PrizeMapper;
import com.jk.luckydraw.service.prize.PrizeService;
import com.jk.luckydraw.service.user.UserService;
import com.jk.luckydraw.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PrizeService prizeService;

    @Value("${img.location}")
    private String location;

    @Value("${img.serverpath}")
    private String serverpath;


    @RequestMapping("delLuckyHistory")
    @ResponseBody
    public Boolean delLuckyHistory(Integer[] ids){
        try{
            userService.delLuckyHistory(ids);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 查询中奖人员历史信息
     * @return
     */
    @RequestMapping("findLuckyHistoryList")
    @ResponseBody
    public HashMap findLuckyHistoryList(){
        HashMap<String, Object> result = new HashMap<>();
        int count = userService.findLuckyHistoryCount();
        List<LuckyHistoryBean> luckyUserBeans = userService.findLuckyHistoryList();
        result.put("data",luckyUserBeans);
        result.put("code",0);
        result.put("count",count);
        return result;
    }

    /**
     * 跳转中奖历史记录查询页
     * @return
     */
    @RequestMapping("toLuckyHistoryPage")
    public String toLuckyHistoryPage(){
        return "luckyhistory";
    }

    /**
     * 保存中奖历史信息
     * @param userId
     * @param prizeId
     * @return
     */
    @RequestMapping("saveLuckyUser")
    @ResponseBody
    public Boolean saveLuckyUser(Integer userId,Integer prizeId){
        try{
            userService.saveLuckyUser(userId,prizeId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 删除参与人员
     * @param ids
     * @return
     */
    @RequestMapping("delPeople")
    @ResponseBody
    public Boolean delPeople(Integer[] ids){
        try {
            userService.delPeople(ids);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询抽奖用户列表
     * @return
     */
    @RequestMapping("findPeopleListPage")
    @ResponseBody
    public HashMap findPeopleListPage(){
        HashMap<String, Object> result = new HashMap<>();
        int count = userService.findPeopleCount();
        List<LuckyUserBean> luckyUserBeans = userService.findPeopleList();
        result.put("data",luckyUserBeans);
        result.put("code",0);
        result.put("count",count);
        return result;
    }

    /**
     * 跳转抽奖人列表页面
     * @return
     */
    @RequestMapping("toPeoplePage")
    public String toPeoplePage(){
        return "people";
    }

    /**
     * 删除奖品
     * @param ids
     * @return
     */
    @RequestMapping("delPrize")
    @ResponseBody
    public Boolean delPrize(Integer[] ids){
        try {
            prizeService.delPrize(ids);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    /**
     * 保存奖品
     * @param prizeBean
     * @param request
     * @return
     */
    @RequestMapping("savePrize")
    @ResponseBody
    private Boolean savePrize(PrizeBean prizeBean,HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            UserBean attribute = (UserBean) session.getAttribute(session.getId());
            prizeBean.setUserId(attribute.getId());
            prizeService.savePrize(prizeBean);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 图片上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("upload")
    @ResponseBody
    public HashMap upload(MultipartFile file,HttpServletRequest request){
        System.out.println("上传");
        HashMap<String, Object> result = new HashMap<>();
        String fileUpload = FileUtil.FileUpload(file, request, location);
        result.put("code",0);

        result.put("imgName",serverpath+fileUpload);
        return result;
    }

    /**
     * 跳转添加奖品页
     * @return
     */
    @RequestMapping("toAddPrizePage")
    public String toAddPrizePage(){
        return "addprize";
    }

    /**
     * 跳转奖品配置页
     * @return
     */
    @RequestMapping("toPrizePage")
    public String toPrizePage(){
        return "prize";
    }

    /**
     * 查看奖品列表
     * @return
     */
    @RequestMapping("findPriceListPage")
    @ResponseBody
    public HashMap<String, Object> findPriceListPage(){
        HashMap<String, Object> result = new HashMap<>();
        int count = prizeService.findPrizeCount();
        List<PrizeBean> priceList = prizeService.findPrizeList();
        result.put("data",priceList);
        result.put("code",0);
        result.put("count",count);
        return result;
    }

    @RequestMapping("findPriceList")
    @ResponseBody
    public List<PrizeBean> findPriceList(){
        return prizeService.findPrizeList();
    }

    /**
     * 获取抽奖人员列表
     * @return
     */
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
