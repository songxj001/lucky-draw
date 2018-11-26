package com.jk.luckydraw.service.lucky;

import com.jk.luckydraw.domain.user.LuckyUserBean;
import com.jk.luckydraw.mapper.lucky.LuckyUserMapper;
import com.jk.luckydraw.utils.FileUtil;
import com.jk.luckydraw.utils.MacUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@Service
public class LuckyUserServiceImpl implements LuckyUserService {

    @Autowired
    private LuckyUserMapper luckyUserMapper;

    @Value("${img.location}")
    private String location;

    @Value("${img.serverpath}")
    private String serverpath;
    @Override
    public HashMap save(LuckyUserBean luckyUserBean, MultipartFile fileImg, HttpServletRequest request) {
        HashMap<String, Object> result = new HashMap<>();
        //获取用户的mac地址
        luckyUserBean.setMac(MacUtil.getMac(request));
        //检查用户是否参与过
        int count = luckyUserMapper.checkUser(luckyUserBean);
        if (count > 0){
            result.put("code",1);
            result.put("msg","您已经参加过了不能在参加了哦");
            result.put("icon",6);
            return result;
        }
        String originalFilename = fileImg.getOriginalFilename();
        String fileUpload = FileUtil.FileUpload(fileImg, request,location);
        luckyUserBean.setPhoto(serverpath+fileUpload);
        try {
            luckyUserMapper.saveLuckyUser(luckyUserBean);
            result.put("code",0);
            result.put("msg","参与成功,祝您中大奖");
            result.put("icon",6);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code",2);
            result.put("msg","网络不给力,请稍后再试");
            result.put("icon",5);
        }
        return result;
    }
}